package com.company.datastructures;

import com.company.miscellaneous.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bioel on 11-Mar-17.
 */
public abstract class DataNode<K> {
     //a list of nodes containing the same key in all the different datastructures
     private final List<Pair<DataStructure<K>,DataNode<K>>> mNodePointers = new ArrayList<>(0);
     public abstract K getKey();
     public abstract void setKey(K key);

     public List<Pair<DataStructure<K>,DataNode<K>>> getNodePointers() {
          return mNodePointers;
     }
}
