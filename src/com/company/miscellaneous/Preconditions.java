package com.company.miscellaneous;

import com.company.datastructures.DataNode;
import com.company.datastructures.tree.RedBlackNode;

/**
 * Created by roman on 3/5/17.
 * I use this to avoid inserting nulls by crashing early
 */
public class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
