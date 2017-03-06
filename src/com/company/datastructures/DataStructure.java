package com.company.datastructures;

/**
 * it's an interface that abstracts over all our datastrctures
 */
public interface DataStructure<T extends Comparable<T>> {
    T find(T t);

    boolean delete(T t);

    boolean add(T t);

    boolean update(T t);
}
