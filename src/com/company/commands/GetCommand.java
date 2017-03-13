package com.company.commands;

import com.company.dataobjects.Customer;
import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.ui.ExecutionState;
import com.company.ui.Logger;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * Created by bioel on 13-Mar-17.
 */
public class GetCommand implements Command<Customer> {
    private final Customer mCustomer;

    public GetCommand(Customer customer) {
        checkNotNull(customer);
        mCustomer = customer;
    }

    @Override
    public void execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure) {
        DataNode<Customer> mainDataNode = mainDataStructure.find(mCustomer);
        if (mainDataNode == null) {
            Logger.log(mCustomer.getCustomerId(), ExecutionState.GET_ERROR_NOT_FOUND);
        } else {
            Customer customer = mainDataNode.getKey();
            Logger.log(customer, ExecutionState.GET_SUCCESS);
        }
    }
}
