package com.company;

import com.company.commands.Command;
import com.company.commands.ExecutionState;
import com.company.comparators.RedBlackNodeCustomerBalanceComparator;
import com.company.dataobjects.Customer;
import com.company.datastructures.list.LinkedList;
import com.company.datastructures.tree.RedBlackNode;
import com.company.datastructures.tree.RedBlackTree;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * Created by bioel on 11-Mar-17.
 */
public class DataStructureManager{
    private final RedBlackTree<Customer> mTree = new RedBlackTree<Customer>(new RedBlackNodeCustomerBalanceComparator());
    private final LinkedList<RedBlackNode<Customer>> mLinkedList = new LinkedList<RedBlackNode<Customer>>(new RedBlackNodeCustomerBalanceComparator());
//    private final heap TODO implement a heap data structure

    public ExecutionState executeCommand(Command command) {
        if (command == null) {
            return ExecutionState.INVALID_COMMAND;
        }
        command.execute(mTree);

    }
}
