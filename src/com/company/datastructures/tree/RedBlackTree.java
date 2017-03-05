package com.company.datastructures.tree;

import com.company.datastructures.DataStructure;

import static com.company.utils.Preconditions.checkNotNull;

public class RedBlackTree<K extends Comparable<K>> implements DataStructure<K> {

    private RedBlackNode<K> mRoot = null;
    private int mSize = 0;


    @Override
    public K find(K k) {
        return search(k).getKey();
    }

    @Override
    public boolean delete(K k) {
        RedBlackNode<K> node = search(k);
        if (node == null) {
            return false;
        }else {
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
        }else {
            return false;
        }
    }

    @Override
    public boolean update(K k) {
        return false;
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

    /**
     * Find successor to a give node.
     *
     * @param x Node to find a successor for.
     * @return The node of the successor.
     */
    private RedBlackNode<K> findSuccessor(RedBlackNode<K> x) {
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
     * Find minimum for sub-tree of given node.
     *
     * @param x Node to find minimum for.
     * @return Node of the minimum.
     */
    private RedBlackNode<K> treeMinimum(RedBlackNode<K> x) {
        while (x.getLeft() != null) {
            x = x.getLeft();
        }
        return x;
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

    /**
     * Tree insert method.
     *
     * @param z node to insert.
     */
    private void insert(RedBlackNode<K> z) {

        RedBlackNode<K> x;
        RedBlackNode<K> y;
        x = null;
        y = mRoot;

        while (y != null) {
            x = y;
            if (z.getKey().compareTo(y.getKey()) < 0) {
                y = y.getLeft();
            } else {
                y = y.getRight();
            }
        }

        z.setParent(x);
        if (x == null) {
            mRoot = z;
        } else {
            if (z.getKey().compareTo(x.getKey()) < 0) {
                x.setLeft(z);
            } else {
                x.setRight(z);
            }
        }
        z.setColor(Color.RED);
        insertFixup(z);
        mSize++;
    }

    private void insertFixup(RedBlackNode<K> z) {
        RedBlackNode<K> y;
        while (z.getParent().getColor() == Color.RED) {
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                y = z.getParent().getParent().getRight();
                if (y.getColor() == Color.RED) {
                    /*Case 1*/
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                    /*End of Case 1*/
                } else {
                    if (z == z.getParent().getRight()) {
                        /*Case 2*/
                        z = z.getParent();
                        leftRotate(z);
                        /*End of Case 2*/
                    }
                    /*Case 3*/
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    rightRotate(z.getParent().getParent());
                    /*End of Case 3*/
                }
            } else {
                y = z.getParent().getParent().getLeft();
                if (y.getColor() == Color.RED) {
                    /*Case 1*/
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {
                        /*Case 2*/
                        z = z.getParent();
                        rightRotate(z);
                        /*End of Case 2*/
                    }
                    /*Case 3*/
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    leftRotate(z.getParent().getParent());
                    /*End of Case 3*/
                }
            }
        }
        mRoot.setColor(Color.BLACK);
    }

    /**
     * Tree delete method.
     *
     * @param z Node to delete.
     */
    private void delete(RedBlackNode<K> z) {
        RedBlackNode<K> y;
        RedBlackNode<K> x;
        Color tempColor = null;
        if (z.getLeft() == null || z.getRight() == null) {
            y = z;
        } else {
            y = findSuccessor(z);
        }
        if (y.getLeft() != null) {
            x = y.getLeft();
        } else {
            x = y.getRight();
        }
        x.setParent(y.getParent());
        if (y.getParent() == null) {
            mRoot = x;
        } else {
            if (y == y.getParent().getLeft()) {
                y.getParent().setLeft(x);
            } else {
                y.getParent().setRight(x);
            }
        }
        if (y != z) {                                // Due to limitation of Java we can't swap nodes (java pass by reference and not pointer).
            y.setLeft(z.getLeft());        // What we do is "turn" y into z this way we can keep all the correct objects
            if (z.getLeft() != null) {     // in the right structure.
                z.getLeft().setParent(y);
            }
            y.setRight(z.getRight());
            if (z.getRight() != null) {
                z.getRight().setParent(y);
            }
            y.setParent(z.getParent());
            if (z.getParent() == null) {
                mRoot = y;
            } else {
                if (z == z.getParent().getLeft()) {
                    z.getParent().setLeft(y);
                } else {
                    z.getParent().setRight(y);
                }
            }
            tempColor = y.getColor();
            y.setColor(z.getColor());

        }
        if (tempColor != null) {
            if (tempColor == Color.BLACK && x != null) {  // Make sure we don't fix around the null
                deleteFixup(x);
            }
        } else {
            if (y.getColor() == Color.BLACK && x != null) { // Make sure we don't fix around the null
                deleteFixup(x);
            }
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
        while (x != mRoot && x.getColor() == Color.BLACK) {
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
}