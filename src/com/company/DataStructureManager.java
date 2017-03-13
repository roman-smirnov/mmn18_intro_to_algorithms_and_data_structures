package com.company;

import com.company.commands.Command;
import com.company.comparators.CustomerBalanceComparator;
import com.company.comparators.CustomerIdComparator;
import com.company.dataobjects.Customer;
import com.company.datastructures.heap.Heap;
import com.company.datastructures.list.LinkedList;
import com.company.datastructures.tree.RedBlackTree;

/**
 *
 */
public class DataStructureManager{
    private final RedBlackTree<Customer> mTree = new RedBlackTree<>(new CustomerIdComparator());
    private final LinkedList<Customer> mLinkedList = new LinkedList<>(new CustomerBalanceComparator());
    private final Heap<Customer> mHeap = new Heap<>(new CustomerBalanceComparator());

    public void executeCommand(Command<Customer> command) {
        if (command != null) {
            command.execute(mTree, mHeap, mLinkedList);
        }
    }
}
