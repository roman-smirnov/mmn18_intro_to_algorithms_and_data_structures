package com.company.datastructures.heap;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;

public class Heap<K> implements DataStructure<K>{
    private static final int NUM_OF_CHILDREN = 2;
    private static final int FIRST_CHILD_TERM = 1;
    private static final int SECOND_CHILD_TERM = 2;
    private static final int DEFAULT_CAPACITY = 10;
    private final ArrayList<DataNode<K>> mArrayList = new ArrayList<>(DEFAULT_CAPACITY);
    private Comparator<K> mComparator;

    public Heap(Comparator<K> comparator) {
        checkNotNull(comparator);
        mComparator = comparator;
    }

    @Override
    public DataNode<K> find(K k) {
        checkNotNull(k);
        for (DataNode<K> dataNode : mArrayList) {
            if (mComparator.compare(dataNode.getKey(), k) == 0) {
                return dataNode;
            }
        }
        return null;
    }

    @Override
    public boolean delete(DataNode<K> node) {
        checkNotNull(node);
//        TODO it takes O(n) to delete because it has to find the object, we need to hold its index to delete in O(1)

        return mArrayList.remove(node);
    }

    @Override
    public boolean update(DataNode<K> node, K k) {
        if (node == null || k == null || mComparator.compare(node.getKey(), k) != 0){
            return false;
        }else {
            node.setKey(k);
            return true;
        }
    }

    @Override
    public DataNode<K> add(K k) {
        //place the element at the bottom of the list
        mArrayList.add(new HeapNode<>(k));
        bubbleUp(mArrayList.size()-1);

    }


    private void swap(int index1, int index2) {
        DataNode<K> tmp = mArrayList.get(index1);
        mArrayList.set(index1,mArrayList.get(index2));
        mArrayList.set(index2, tmp);
    }

    private void bubbleUp(int index) {
        if (isIndexValid(index) && isIndexValid(getParentIndex(index))){
            K parentKey = mArrayList.get(getParentIndex(index)).getKey();
            K childKey =  mArrayList.get(index).getKey();
            if (mComparator.compare(parentKey, childKey) < 0) {
                swap(getParentIndex(index),index);
                if (getParentIndex(index) > 0) {
                    bubbleUp(getParentIndex(index));
                }
            }
        }
    }

    private int getParentIndex(int childIndex) {
        return ((childIndex+1)/2)+1;
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < mArrayList.size();
    }
}