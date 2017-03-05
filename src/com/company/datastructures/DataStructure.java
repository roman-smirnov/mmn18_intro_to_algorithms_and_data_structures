package com.company.datastructures;

/**
 * Created by roman on 3/5/17.
 */
public interface DataStructure<T extends Comparable<T>> {
    T find(T t);

    boolean delete(T t);

    boolean add(T t);

    boolean update(T t);
}
