package com.company.datastructures.list;

import com.company.datastructures.DataNode;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * Created by bioel on 11-Mar-17.
 */
public class LinkedListNode<K> extends DataNode<K> {
    private K mKey;
    private LinkedListNode<K> mPrevious = null;
    private LinkedListNode<K> mNext = null;

    public LinkedListNode(K key) {
        checkNotNull(key);
        mKey = key;
    }

    public void setPrevious(LinkedListNode<K> previous) {
        mPrevious = previous;
    }

    public void setNext(LinkedListNode<K> next) {
        mNext = next;
    }

    public LinkedListNode<K> getNext() {
        return mNext;
    }

    public LinkedListNode<K> getPrevious() {
        return mPrevious;
    }

    @Override
    public K getKey() {
        return mKey;
    }

    public void setKey(K key) {
        checkNotNull(key);
        mKey = key;
    }
}
