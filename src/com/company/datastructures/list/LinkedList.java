package com.company.datastructures.list;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;

import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;


/**
 * Created by roman on 3/5/17.
 */
public class LinkedList<K> implements DataStructure<K> {

    private LinkedListNode<K> mRoot = null;
    private Comparator<K> mComparator;

    public LinkedList(Comparator<K> comparator) {
        mComparator = comparator;
    }

    @Override
    public DataNode<K> find(K k) {
        LinkedListNode<K> currentNode = mRoot;
        //find a node with specified key in the list
        while (currentNode != null) {
            if (mComparator.compare(currentNode.getKey(), k) == 0) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        //didn't find a node with specified key
        return null;
    }

    @Override
    public boolean delete(DataNode<K> node) {
        checkNotNull(node);
        LinkedListNode<K> delNode = convertToLinkedNode(node);
        if (delNode == null) {
            return false;
        }
        if (delNode.getPrevious() != null && delNode.getNext() != null) {
            delNode.getPrevious().setNext(delNode.getNext());
            return true;
        } else if (delNode.getPrevious() == null && delNode.getNext()==null) {
            mRoot = null;
            return true;
        } else if (delNode.getPrevious() == null) {
            mRoot = delNode.getNext();
            return true;
        }else{
            delNode.getPrevious().setNext(null);
            return true;
        }
    }

    @Override
    public DataNode<K> add(K k) {
        LinkedListNode<K> newNode = new LinkedListNode<>(k);
        newNode.setNext(mRoot);
        mRoot.setPrevious(newNode);
        mRoot = newNode;
        return newNode;
    }

    @Override
    public boolean update(DataNode<K> node, K k) {
        LinkedListNode<K> updateNode = convertToLinkedNode(node);
        if ( updateNode == null || mComparator.compare(updateNode.getKey(), k) != 0) {
            return false;
        }
        updateNode.setKey(k);
        return true;
    }

    @Override
    public DataNode<K> getMax() {
        LinkedListNode<K> currentNode = mRoot;
        LinkedListNode<K> maxKeyNode = mRoot;

        //find a node with specified key in the list
        while (currentNode != null) {
            if (mComparator.compare(currentNode.getKey(), maxKeyNode.getKey()) > 0) {
                maxKeyNode = currentNode;
            }
            currentNode = currentNode.getNext();
        }
        return maxKeyNode;
    }

    /**
     * converts a DataNode to a LinkedListNode of same type
     * @param node
     * @return
     */
    private LinkedListNode<K> convertToLinkedNode(DataNode<K> node) {
        checkNotNull(node);
        // cast node to RedBlackNode because we need to use methods specific to it
        if (node instanceof LinkedListNode) {
            return (LinkedListNode<K>) node;
        }else {
            return null;
        }
    }
}
