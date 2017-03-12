package com.company.datastructures.heap;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.datastructures.tree.RedBlackNode;

import java.util.ArrayList;
import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;

public class Heap<K> implements DataStructure<K>{
    private static final int DEFAULT_CAPACITY = 10;
    private static final int FIRST_SON_INDEX = 0;
    private static final int SECOND_SON_INDEX = 1;

    private final ArrayList<HeapNode<K>> mArrayList = new ArrayList<>(DEFAULT_CAPACITY);
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
        HeapNode<K> heapNode = convertToHeapNode(node);
        if (heapNode != null) {
            swap(heapNode.getIndex(),mArrayList.size()-1);
            mArrayList.remove(mArrayList.size() - 1);
            bubbleDown(heapNode.getIndex());
            return true;
        }else {
            return false;
        }
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
        HeapNode<K> heapNode = new HeapNode<>(k,mArrayList.size());
        mArrayList.add(heapNode);
        bubbleUp(mArrayList.size()-1);
        return heapNode;
    }

    @Override
    public DataNode<K> getMax() {
        if (mArrayList.size() > 0) {
            return mArrayList.get(mArrayList.size() - 1);
        }else {
            return null;
        }
    }

    private void swap(int index1, int index2) {
        HeapNode<K> tmp = mArrayList.get(index1);
        mArrayList.set(index1,mArrayList.get(index2));
        mArrayList.set(index2, tmp);
        //set the node indices
        mArrayList.get(index1).setIndex(index2);
        mArrayList.get(index2).setIndex(index1);

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
    private void bubbleDown(int index) {
        if (!isIndexValid(index)) {
            return;
        }
        K largestKey = mArrayList.get(index).getKey();
        int largestIndex = index;

        int firstChildIndex = getSon(index, FIRST_SON_INDEX);
        if (isIndexValid(firstChildIndex) && mComparator.compare(largestKey,mArrayList.get(firstChildIndex).getKey()) < 0) {
            largestKey = mArrayList.get(firstChildIndex).getKey();
            largestIndex = firstChildIndex;
        }
        int secondChildIndex = getSon(index, SECOND_SON_INDEX);
        if (isIndexValid(secondChildIndex) && mComparator.compare(largestKey,mArrayList.get(secondChildIndex).getKey()) < 0) {
            largestKey = mArrayList.get(secondChildIndex).getKey();
            largestIndex = secondChildIndex;
        }

        if (index != largestIndex) {
            swap(index, largestIndex);
            bubbleDown(largestIndex);
        }
    }

    /**
     *
     * @param index
     * @param childIndex 0 for first son, 1 for second son
     * @return
     */
    private int getSon(int index, int childIndex) {
        int sonIndex = 2 * index + childIndex +1;
        if (!isIndexValid(sonIndex)) {
            return -1;
        }
        return sonIndex;
    }
    private int getParentIndex(int childIndex) {
        return ((childIndex+1)/2)+1;
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < mArrayList.size();
    }

    /**
     * converts a DataNode to a RedBlackNode of same type
     * @param node
     * @return
     */
    private HeapNode<K> convertToHeapNode(DataNode<K> node) {
        checkNotNull(node);
        // cast node to subtype because we need to use methods specific to it
        if (node instanceof RedBlackNode) {
            return (HeapNode<K>) node;
        }else {
            return null;
        }
    }
}