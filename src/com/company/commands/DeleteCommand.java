package com.company.commands;

import com.company.dataobjects.Customer;
import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.miscellaneous.Pair;
import com.company.ui.ExecutionState;
import com.company.ui.Logger;


/**
 *  the delete command
 */
public final class DeleteCommand implements Command<Customer> {
    private final Customer mCustomer;

    public DeleteCommand(Customer customer) {
        mCustomer = customer;
    }

    @Override
    public void execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure ) {
        DataNode<Customer> mainDataNode = mainDataStructure.find(mCustomer);
        if (mainDataNode == null) {
            Logger.log(mCustomer.getCustomerId(), ExecutionState.DELETE_ERROR_NOT_FOUND);
        } else if (mainDataNode.getKey().getBalance() != 0) {
            Logger.log(mCustomer.getCustomerId(),ExecutionState.DELETE_ERROR_BALANCE_NOT_ZERO);
        } else {
            //delete from other data structures
            for (Pair<DataStructure<Customer>, DataNode<Customer>> dataPair : mainDataNode.getNodePointers()) {
                dataPair.getLeft().delete(dataPair.getRight());
            }
            //delete from main data structure
            mainDataStructure.delete(mainDataNode);
            Logger.log(mCustomer,ExecutionState.DELETE_SUCCESS);
        }
    }


}
