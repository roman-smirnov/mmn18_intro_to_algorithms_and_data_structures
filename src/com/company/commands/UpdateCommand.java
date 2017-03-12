package com.company.commands;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.dataobjects.Customer;
import com.company.miscellaneous.Pair;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 *  the update command
 */
public final class UpdateCommand implements Command<Customer>{
    private final Customer mCustomer;

    public UpdateCommand(Customer customer) {
        checkNotNull(customer);
        mCustomer = customer;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure ) {
        DataNode<Customer> mainDataNode = mainDataStructure.find(mCustomer);
        if (mainDataNode == null) {
            return ExecutionState.ERROR_NOT_FOUND;
        }else{
            //delete from other data structures
            for (Pair<DataStructure<Customer>, DataNode<Customer>> dataPair : mainDataNode.getNodePointers()) {
                dataPair.getLeft().update(dataPair.getRight(),mCustomer);
            }
            //delete from main data structure
            mainDataStructure.update(mainDataNode, mCustomer);
            return ExecutionState.SUCCESS_UPDATE;
        }
    }
}
