package com.company.datastructures;

/**
 * an interface which abstracts over all our data-structures
 */
public interface DataStructure<K> {
//    find a node with the specified key
    DataNode<K> find(K k);
//    delete the given node
    boolean delete(DataNode<K> node);
//    add a node with given key to the data-structure
    DataNode<K> add(K k);
//    update the given node with the given key
    boolean update(DataNode<K> node, K k);
}
