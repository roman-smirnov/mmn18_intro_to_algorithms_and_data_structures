package com.company.commands;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.dataobjects.Customer;
import com.company.miscellaneous.Pair;
import com.company.ui.ExecutionState;
import com.company.ui.Logger;

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
    public void execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure ) {
        DataNode<Customer> mainDataNode = mainDataStructure.find(mCustomer);
        if (mainDataNode == null) {
            Logger.log(mCustomer, ExecutionState.UPDATE_ERROR_NOT_FOUND);
        }else{
            //customer is immutable so we have to create a new one
            Customer updatedCustomer = new Customer(mCustomer.getFirstName(), mCustomer.getLastName(), mCustomer.getId(), mCustomer.getCustomerId(), mCustomer.getBalance() + mainDataNode.getKey().getBalance());
            //delete from other data structures
            for (Pair<DataStructure<Customer>, DataNode<Customer>> dataPair : mainDataNode.getNodePointers()) {
                dataPair.getLeft().update(dataPair.getRight(), updatedCustomer);
            }
            //delete from main data structure
            mainDataStructure.update(mainDataNode, updatedCustomer);
            Logger.log(updatedCustomer,ExecutionState.UPDATE_SUCCESS);
        }
    }
}
