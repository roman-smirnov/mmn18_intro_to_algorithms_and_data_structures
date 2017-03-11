package com.company.datastructures.tree;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;

import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * implementation of a red black tree
 * The Key and Value are one and the same because it implements comparable
 * all methods essentially copied from the book - modified to not use the nill object pattern
 * @param <K>
 */
public class RedBlackTree<K> implements DataStructure<K> {

    //the root node
    private RedBlackNode<K> mRoot = null;
    private final Comparator<K> mComparator;

    public RedBlackTree(Comparator<K> comparator) {
        checkNotNull(comparator);
        mComparator = comparator;
    }

    /**
     * return the node in the tree containing the key given as parameter, null otherwise
     * @param key
     * @return the node containing the given key
     */
    @Override
    public DataNode<K> find(K key) {
        checkNotNull(key);
        RedBlackNode<K> currentNode;
        currentNode = mRoot;

        while (currentNode != null &&  mComparator.compare(key, currentNode.getKey()) != 0) {
            if (mComparator.compare(key,currentNode.getKey()) < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return currentNode;
    }


    /**
     * Tree delete method.
     * The node to delete must not be null and must be within the tree!
     * @param node Node to delete.
     */
    @Override
    public boolean delete(DataNode<K> node) {
        RedBlackNode<K> delNode = convertToRedBlack(node);
        if (delNode == null) {
            return false;
        }
        RedBlackNode<K> y;
        RedBlackNode<K> x;
        Color tempColor = null;
        //check if one of the children is null
        if (delNode.getLeft() == null || delNode.getRight() == null) {
            y = delNode;
        } else {
            y = treeSuccessor(delNode);
        }
        if (y.getLeft() != null) {
            x = y.getLeft();
        } else {
            x = y.getRight();
        }
        if (x != null) {
            x.setParent(y.getParent());
        }
        if (y.getParent() == null) {
            mRoot = x;
        } else if (y == y.getParent().getLeft()) {
            y.getParent().setLeft(x);
        } else {
            y.getParent().setRight(x);
        }
        if (y != delNode) {
            delNode.setKey(y.getKey());
        }
        if (isBlack(y) && x != null) {
            deleteFixup(x);
        }
        return true;
    }


    /**
     * insert a new node into our red black tree
     * @param k
     */
    @Override
    public boolean add(K k) {
        if (find(k) != null) {
            return false;
        }else {
            insert(new RedBlackNode<K>(k));
            return true;
        }
    }

    /**
     * insert a new node into our red black tree
     * @param newNode
     */
    private void insert(RedBlackNode<K> newNode) {
        checkNotNull(newNode);
        RedBlackNode<K> y = null;
        RedBlackNode<K> x = mRoot;
        //go down the tree until x is a null leaft
        while (x != null) {
            y = x;
            if (mComparator.compare(newNode.getKey(),x.getKey()) < 0) {
                x = x.getLeft();
            }else{
                x = x.getRight();
            }
        }
        //set x's parent to be newNode's parent
        newNode.setParent(y);
        //if newNode's parent is null, then newNode is the root
        if (y == null) {
            mRoot = newNode;
//            place the newNode as the left/right child of its parent according to its value
        } else if (mComparator.compare(newNode.getKey(),y.getKey()) < 0) {
            y.setLeft(newNode);
        }else{
            y.setRight(newNode);
        }
        //fix the tree
        insertFixup(newNode);
    }


    @Override
    public boolean update(DataNode<K> node, K k) {
        RedBlackNode<K> updateNode = convertToRedBlack(node);
        if ( updateNode == null || mComparator.compare(updateNode.getKey(), k) != 0) {
            return false;
        }
        updateNode.setKey(k);
        return true;
    }

    /**
     * converts a DataNode to a RedBlackNode of same type
     * will throw IllegalArgumentException if it's not RedBlackNode
     * @param node
     * @return
     */
    private RedBlackNode<K> convertToRedBlack(DataNode<K> node) {
        checkNotNull(node);
        // cast node to RedBlackNode because we need to use methods specific to it
        if (node instanceof RedBlackNode) {
            return (RedBlackNode<K>) node;
        }else {
            return null;
        }
    }

    /**
     * fix the tree after inserting a new node
     * @param newNode
     */
    private void insertFixup(RedBlackNode<K> newNode) {
        RedBlackNode<K> uncle;
        while (isRed(newNode.getParent())) {
            // check if we're in a left leaning branch
            if (newNode.getParent() == newNode.getParent().getParent().getLeft()) {
                //the uncle is grandfather's right son
                uncle = newNode.getParent().getParent().getRight();
                if (isRed(uncle)) {
                    // case 1
                    newNode.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    newNode.getParent().getParent().setColor(Color.RED);
                    newNode = newNode.getParent().getParent();
                } else if (newNode == newNode.getParent().getRight()) {
                    // case 2
                    newNode = newNode.getParent();
                    leftRotate(newNode);
                } else {
                    // case 3
                    newNode.getParent().setColor(Color.BLACK);
                    newNode.getParent().getParent().setColor(Color.RED);
                    rightRotate(newNode.getParent().getParent());
                }
            } else {
                //we're in a right leaning branch
                // the uncle is the grandfather's left branch
                uncle = newNode.getParent().getParent().getLeft();
                if (isRed(uncle)) {
//                    case 1
                    newNode.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    newNode.getParent().getParent().setColor(Color.RED);
                    newNode = newNode.getParent().getParent();
                } else if (newNode == newNode.getParent().getLeft()) {
//                    case 2
                    newNode = newNode.getParent();
                    rightRotate(newNode);
                } else {
//                    case 3
                    newNode.getParent().setColor(Color.BLACK);
                    newNode.getParent().getParent().setColor(Color.RED);
                    leftRotate(newNode.getParent().getParent());
                }
            }
        }
        mRoot.setColor(Color.BLACK);
    }



    /**
     * Delete fixup helper method.
     *
     * @param x Node to fix.
     */
    private void deleteFixup(RedBlackNode<K> x) {
        RedBlackNode<K> w;
        while (x != mRoot && isBlack(x)) {
            if (x == x.getParent().getLeft()) {
                w = x.getParent().getRight();
                if (w.getColor() == Color.RED) {
//                    case 1
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }
                if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
//                    case 2
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == Color.BLACK) {
//                        case 3
                        w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rightRotate(w);
                        w = x.getParent().getRight();
                    }
//                    case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getRight().setColor(Color.BLACK);
                    leftRotate(x.getParent());
                    x = mRoot;
                }
            } else {
                w = x.getParent().getLeft();
                if (w.getColor() == Color.RED) {
//                    case 1
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor() == Color.BLACK && w.getLeft().getColor() == Color.BLACK) {
//                    case 2
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == Color.BLACK) {
//                        case 3
                        w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        leftRotate(w);
                        w = x.getParent().getLeft();
                    }
//                    case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getLeft().setColor(Color.BLACK);
                    rightRotate(x.getParent());
                    x = mRoot;
                }
            }
        }
    }

    /**
     * check whether a node is red ( a null node is black)
     *
     * @param node
     * @return
     */
    private boolean isRed(RedBlackNode<K> node) {
        return node != null && node.getColor() == Color.RED;
    }

    /**
     * check whether a node is black ( a null node is black)
     *
     * @param node
     * @return
     */
    private boolean isBlack(RedBlackNode<K> node) {
        return node == null || node.getColor() == Color.BLACK;
    }

 

    /**
     * find the tree successor of a node given as parameter
     * @param x
     * @return
     */
    private RedBlackNode<K> treeSuccessor(RedBlackNode<K> x) {
        RedBlackNode<K> y;
        if (x.getRight() != null) {
            return treeMinimum(x.getRight());
        }
        y = x.getParent();
        while (y != null && x == y.getRight()) {
            x = y;
            y = y.getParent();
        }
        return y;
    }

    /**
     * find the minimum key of the tree
     * @param x
     * @return
     */
    private RedBlackNode<K> treeMinimum(RedBlackNode<K> x) {
        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
    }


    /**
     * right rotation
     * @param x
     */
    private void rightRotate(RedBlackNode<K> x) {
        checkNotNull(x);
        RedBlackNode<K> y;
        y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            mRoot = y;
        } else {
            if (x == x.getParent().getRight()) {
                x.getParent().setRight(y);
            } else {
                x.getParent().setLeft(y);
            }
        }
        y.setRight(x);
        x.setParent(y);
    }

    /**
     * left rotation
     * @param x
     */
    private void leftRotate(RedBlackNode<K> x) {
        RedBlackNode<K> y;
        y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            mRoot = y;
        } else {
            if (x == x.getParent().getLeft()) {
                x.getParent().setLeft(y);
            } else {
                x.getParent().setRight(y);
            }
        }
        y.setLeft(x);
        x.setParent(y);
    }
}