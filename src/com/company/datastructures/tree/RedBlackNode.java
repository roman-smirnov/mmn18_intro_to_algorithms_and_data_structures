package com.company.datastructures.tree;


/**
 * Created by roman on 3/4/17.
 */
public class RedBlackNode<T extends Comparable> {
    private Color mColor = Color.RED;
    private T mKey = null;
    private RedBlackNode<T> mParent = null;
    private RedBlackNode<T> mLeft = null;
    private RedBlackNode<T> mRight = null;

    public RedBlackNode(T mKey) {
        this.mKey = mKey;
    }

    public Color getColor() {
        return mColor;
    }

    public T getKey() {
        return mKey;
    }

    public RedBlackNode<T> getParent() {
        return mParent;
    }

    public RedBlackNode<T> getLeft() {
        return mLeft;
    }

    public RedBlackNode<T> getRight() {
        return mRight;
    }

    public void setColor(Color color) {
        mColor = color;
    }

    public void setParent(RedBlackNode parent) {
        mParent = parent;
    }

    public void setLeft(RedBlackNode<T> left) {
        mLeft = left;
    }

    public void setRight(RedBlackNode<T> right) {
        mRight = right;
    }
}
