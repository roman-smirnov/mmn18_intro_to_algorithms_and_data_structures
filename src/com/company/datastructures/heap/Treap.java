package com.company.datastructures.heap;

import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * Created by roman on 3/5/17.
 */
public class Treap<K> {
    //the root node
    private TreapNode<K> mRoot = null;
    private final Comparator<K> mComparator;

    public Treap(Comparator<K> comparator) {
        checkNotNull(comparator);
        mComparator = comparator;
    }

}
