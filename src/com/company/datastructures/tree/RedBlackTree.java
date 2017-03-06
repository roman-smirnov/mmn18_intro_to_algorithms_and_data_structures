package com.company.datastructures.tree;

import com.company.datastructures.DataStructure;

import static com.company.utils.Preconditions.checkNotNull;

public class RedBlackTree<K extends Comparable<K>> implements DataStructure<K> {

    private RedBlackNode<K> mRoot = null;
    private final RedBlackNode<K> mNill = new RedBlackNode<>(null);

    @Override
    public K find(K k) {
        RedBlackNode<K> node = search(k);
        if (node == null) {
            return null;
        } else {
            return search(k).getKey();
        }
    }

    @Override
    public boolean delete(K k) {
        RedBlackNode<K> node = search(k);
        if (node == null) {
            return false;
        } else {
            delete(node);
            return true;
        }
    }

    @Override
    public boolean add(K k) {
        RedBlackNode<K> node = search(k);
        if (node == null) {
            insert(new RedBlackNode<K>(k));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(K k) {
        //find the node with given key
        RedBlackNode<K> updateNode = search(k);
        if (updateNode == null) {
            return false;
        } else {
            //we can do this because the keys are the ids and the value we're replacing is the balance
            updateNode.setKey(k);
            return true;
        }
    }

    private RedBlackNode<K> search(K key) {
        checkNotNull(key);
        RedBlackNode<K> currentNode;
        currentNode = mRoot;
        while (currentNode != null && !key.equals(currentNode.getKey())) {
            if (key.compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return currentNode;
    }

    private RedBlackNode<K> successor(RedBlackNode<K> x) {
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

    private void insert(RedBlackNode<K> newNode) {
        checkNotNull(newNode);

        RedBlackNode<K> x;
        RedBlackNode<K> y;
        x = null;
        y = mRoot;

        //travel down the tree until reaching the appropriate leaf
        while (y != null) {
            x = y;
            if (newNode.getKey().compareTo(y.getKey()) < 0) {
                y = y.getLeft();
            } else {
                y = y.getRight();
            }
        }
        newNode.setParent(x);
        // set it as the root node
        if (x == null) {
            mRoot = newNode;
        } else if (newNode.getKey().compareTo(x.getKey()) < 0) {
            //set the new node as left or right child of its parent
            x.setLeft(newNode);
        } else {
            //set the new node as the right child of its parent
            x.setRight(newNode);
        }
        //fix the tree
        insertFixup(newNode);
        mSize++;
    }

    private void insertFixup(RedBlackNode<K> z) {
        RedBlackNode<K> uncle;
        while (isRed(z.getParent())) {
            // check if we're in a left leaning branch
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                //the uncle is grandfather's right son
                uncle = z.getParent().getParent().getRight();
                if (isRed(uncle)) {
                    // case 1
                    z.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else if (z == z.getParent().getRight()) {
                    // case 2
                    z = z.getParent();
                    leftRotate(z);
                } else {
                    // case 3
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    rightRotate(z.getParent().getParent());
                }
            } else {
                //we're in a right leaning branch
                // the uncle is the grandfather's left branch
                uncle = z.getParent().getParent().getLeft();
                if (isRed(uncle)) {
//                    case 1
                    z.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else if (z == z.getParent().getLeft()) {
//                    case 2
                    z = z.getParent();
                    rightRotate(z);
                } else {
//                    case 3
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    leftRotate(z.getParent().getParent());
                }
            }
        }
        mRoot.setColor(Color.BLACK);
    }

    /**
     * Tree delete method.
     * The node to delete must not be null and must be within the tree!
     * @param delNode Node to delete.
     */
    private void delete(RedBlackNode<K> delNode) {
        checkNotNull(delNode);
        RedBlackNode<K> y;
        RedBlackNode<K> x;
        Color tempColor = null;
        //check if either child is null
        if (delNode.getLeft() == null || delNode.getRight() == null) {
            y = delNode;
        } else {
            y = successor(delNode);
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
            delNode.setLeft(y.getLeft());
            delNode.setRight(y.getRight());
            delNode.setParent(y.getParent());
        }
        if (isBlack(y)) {
            deleteFixup(x);
        }
        mSize--;
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
                    /*Case 1*/
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                    /*End of Case 1*/
                }
                if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
                    /*Case 2*/
                    w.setColor(Color.RED);
                    x = x.getParent();
                    /*End of Case 2*/
                } else {
                    if (w.getRight().getColor() == Color.BLACK) {
                        /*Case 3*/
                        w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rightRotate(w);
                        w = x.getParent().getRight();
                        /*End of Case 3*/
                    }
                    /*Case 4*/
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getRight().setColor(Color.BLACK);
                    leftRotate(x.getParent());
                    x = mRoot;
                }
            } else {
                w = x.getParent().getLeft();
                if (w.getColor() == Color.RED) {
                    /*Case 1*/
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                    /*End of Case 1*/
                }
                if (w.getRight().getColor() == Color.BLACK && w.getLeft().getColor() == Color.BLACK) {
                    /*Case 2*/
                    w.setColor(Color.RED);
                    x = x.getParent();
                    /*End of Case 2*/
                } else {
                    if (w.getLeft().getColor() == Color.BLACK) {
                        /*Case 3*/
                        w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        leftRotate(w);
                        w = x.getParent().getLeft();
                        /*End of Case 3*/
                    }
                    /*Case 4*/
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
}