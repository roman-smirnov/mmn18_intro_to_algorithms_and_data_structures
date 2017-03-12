package com.company.datastructures.heap;


/**
 * This class is used to construct and perform operations on arrays
 */
public class Heapifier {

    //holds the max number of children of a node
    private static final int NUM_OF_CHILDREN =2;


    /**
     * returns the nth child (zero indexed) of the ith element in the array
     * @param arr
     * @param i
     * @param son
     * @return
     */
    private int getNthSon(int[] arr, int i, int son) {
        int sonIndex = NUM_OF_CHILDREN * i + son+1;
        if (sonIndex >= arr.length) {
            return -1;
        }
        return sonIndex;
    }



    /**
     * swap the values of two elements in a given array
     * @param arr
     * @param posA
     * @param posB
     */
    private void swap(int[] arr, StatKeeper statKeeper, int posA, int posB) {
        int temp = arr[posA];
        arr[posA] = arr[posB];
        arr[posB]= temp;
        statKeeper.numberOfAssignments += 3;
    }
}

