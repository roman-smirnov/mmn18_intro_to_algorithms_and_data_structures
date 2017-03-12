package com.company.commands;

import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.dataobjects.Customer;
import com.company.miscellaneous.Pair;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * the new customer command
 */
public final class NewCommand implements Command<Customer>{
    private final Customer mCustomer;

    public NewCommand(Customer customer) {
        checkNotNull(customer);
        mCustomer = customer;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure ) {
        //try to add to main data structure
        DataNode<Customer> mainNode = mainDataStructure.add(mCustomer);

        if(mainNode != null) {
            //now add to other data structures and save pointers to other nodes in main node
            mainNode.getNodePointers().add(new Pair<>(mainDataStructure, secondaryDataStructure.add(mCustomer)));
            mainNode.getNodePointers().add(new Pair<>(secondaryDataStructure, tertiaryDataStructure.add(mCustomer)));
            return ExecutionState.SUCCESS_ADD;
        }else{
            return ExecutionState.ERROR_ALREADY_EXISTS;
        }


    }
}
