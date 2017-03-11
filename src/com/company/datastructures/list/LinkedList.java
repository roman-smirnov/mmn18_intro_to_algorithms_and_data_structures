package com.company.datastructures.list;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.datastructures.tree.RedBlackNode;

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
        return false;
    }

    @Override
    public boolean add(K k) {
        return false;
    }

    @Override
    public boolean update(DataNode<K> node, K k) {
        return false;
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
