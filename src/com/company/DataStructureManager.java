package com.company;

import com.company.commands.Command;
import com.company.commands.ExecutionState;
import com.company.comparators.CustomerIdComparator;
import com.company.dataobjects.Customer;
import com.company.datastructures.list.LinkedList;
import com.company.datastructures.tree.RedBlackNode;
import com.company.datastructures.tree.RedBlackTree;

/**
 * Created by bioel on 11-Mar-17.
 */
public class DataStructureManager{
    private final RedBlackTree<Customer> mTree = new RedBlackTree<Customer>(new CustomerIdComparator());
    private final LinkedList<Customer> mLinkedList = new LinkedList<RedBlackNode<Customer>>(new CustomerIdComparator());
//    private final heap TODO implement a heap data structure

    public ExecutionState executeCommand(Command command) {
        if (command == null) {
            return ExecutionState.INVALID_COMMAND;
        }
        command.execute(mTree);

    }
}
