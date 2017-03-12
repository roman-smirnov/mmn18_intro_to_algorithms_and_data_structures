package com.company.datastructures;

import com.company.miscellaneous.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bioel on 11-Mar-17.
 */
public abstract class DataNode<K> {
     private final List<Pair<DataStructure<K>,DataNode<K>>> mContemporaries = new ArrayList<>(0);
     public abstract K getKey();

     public List<Pair<DataStructure<K>,DataNode<K>>> getNodePointers() {
          return mContemporaries;
     }
}
