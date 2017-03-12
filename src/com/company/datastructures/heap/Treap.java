package com.company.datastructures.heap;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;

import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * Created by roman on 3/5/17.
 */
public class Treap<K> implements DataStructure<K> {
    //the root node
    private TreapNode<K> mRoot = null;
    private final Comparator<K> mComparator;

    public Treap(Comparator<K> comparator) {
        checkNotNull(comparator);
        mComparator = comparator;
    }

    @Override
    public DataNode<K> find(K k) {
        return null;
    }

    @Override
    public boolean delete(DataNode<K> node) {
        return false;
    }

    @Override
    public DataNode<K> add(K k) {
        return null;
    }

    @Override
    public boolean update(DataNode<K> node, K key) {
        if (node == null || mComparator.compare(node.getKey(), key) != 0) {
            return false;
        }else {
            node.setKey(key);
            return true;
        }
    }
}
