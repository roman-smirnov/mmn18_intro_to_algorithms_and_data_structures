package com.company.datastructures.tree;


import com.company.datastructures.DataNode;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * Created by roman on 3/4/17.
 * a red black node in our red black tree
 */
public class RedBlackNode<K> extends DataNode<K> {
    private Color mColor = Color.RED;
    private K mKey = null;
    private RedBlackNode<K> mParent = null;
    private RedBlackNode<K> mLeft = null;
    private RedBlackNode<K> mRight = null;

    public RedBlackNode(K mKey) {
        this.mKey = mKey;
    }

    public Color getColor() {
        return mColor;
    }

    @Override
    public K getKey() {
        return mKey;
    }

    public RedBlackNode<K> getParent() {
        return mParent;
    }

    public RedBlackNode<K> getLeft() {
        return mLeft;
    }

    public RedBlackNode<K> getRight() {
        return mRight;
    }

    public void setColor(Color color) {
        mColor = color;
    }

    public void setParent(RedBlackNode<K> parent) {
        mParent = parent;
    }

    public void setLeft(RedBlackNode<K> left) {
        mLeft = left;
    }

    public void setRight(RedBlackNode<K> right) {
        mRight = right;
    }

    public void setKey(K key) {
        checkNotNull(key);
        mKey = key;
    }
}
