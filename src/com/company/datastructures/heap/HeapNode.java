package com.company.datastructures.heap;

import com.company.datastructures.DataNode;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * a heapnode for a heap
 */
public class HeapNode<K> extends DataNode<K> {
    private K mKey;
    private int mIndex;

    public HeapNode(K key, int index) {
        checkNotNull(key);
        mKey = key;
        mIndex = index;
    }

    @Override
    public K getKey() {
        return mKey;
    }

    @Override
    public void setKey(K key) {
        checkNotNull(key);
        mKey = key;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }
}
