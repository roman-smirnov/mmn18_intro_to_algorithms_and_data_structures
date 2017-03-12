package com.company.commands;

import com.company.dataobjects.Customer;
import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.miscellaneous.Pair;


/**
 *  the delete command
 */
public final class DeleteCommand implements Command<Customer> {
    private final Customer mCustomer;

    public DeleteCommand(Customer customer) {
        mCustomer = customer;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure ) {
        DataNode<Customer> mainDataNode = mainDataStructure.find(mCustomer);
        if (mainDataNode == null) {
            return ExecutionState.ERROR_NOT_FOUND;
        } else if (mainDataNode.getKey().getBalance() != 0) {
            return ExecutionState.ERROR_BALANCE_NOT_ZERO;
        } else {
            //delete from other data structures
            for (Pair<DataStructure<Customer>, DataNode<Customer>> dataPair : mainDataNode.getNodePointers()) {
                dataPair.getLeft().delete(dataPair.getRight());
            }
            //delete from main data structure
            mainDataStructure.delete(mainDataNode);
            return ExecutionState.SUCCESS_DELETE;
        }
    }


}
