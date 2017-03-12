package com.company.commands;

import com.company.dataobjects.Customer;
import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.miscellaneous.Pair;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 *  the update command
 */
public final class MaxCommand implements Command<Customer>{

    @Override
    public ExecutionState execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure ) {
        DataNode<Customer> maxDataNode = tertiaryDataStructure.getMax();
        if (maxDataNode == null) {
            System.out.println("no clients in datastructure");
            return ExecutionState.ERROR_NOT_FOUND;
        }else{
            System.out.println("The customer with the max balance is: " + maxDataNode.getKey().toString());
            return ExecutionState.SUCCESS_MAX;
        }
    }
}
