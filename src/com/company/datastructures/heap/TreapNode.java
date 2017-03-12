package com.company.datastructures.heap;


import com.company.datastructures.DataNode;

/**
 * Created by roman on 3/4/17.
  * a node in a treap
 */
public class TreapNode<K> extends DataNode<K> {
    private K mKey = null;
    private TreapNode<K> mParent = null;
    private TreapNode<K> mLeft = null;
    private TreapNode<K> mRight = null;

    public TreapNode(K mKey) {
        this.mKey = mKey;
    }


    @Override
    public K getKey() {
        return mKey;
    }

    public TreapNode<K> getParent() {
        return mParent;
    }

    public TreapNode<K> getLeft() {
        return mLeft;
    }

    public TreapNode<K> getRight() {
        return mRight;
    }

    public void setParent(TreapNode<K> parent) {
        mParent = parent;
    }

    public void setLeft(TreapNode<K> left) {
        mLeft = left;
    }

    public void setRight(TreapNode<K> right) {
        mRight = right;
    }

    public void setKey(K key) {
        mKey = key;
    }
}
