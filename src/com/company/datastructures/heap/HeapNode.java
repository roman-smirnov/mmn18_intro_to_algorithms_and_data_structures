package com.company.datastructures.heap;

import com.company.datastructures.DataNode;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * Created by bioel on 12-Mar-17.
 */
public class HeapNode<K> extends DataNode<K> {
    private K mKey;

    public HeapNode(K key) {
        checkNotNull(key);
        mKey = key;
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
}
