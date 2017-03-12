package com.company.commands;

import com.company.dataobjects.Customer;
import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.miscellaneous.Pair;
import com.company.ui.Logger;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 *  the update command
 */
public final class MaxCommand implements Command<Customer>{

    @Override
    public void execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure ) {
        DataNode<Customer> maxDataNode = tertiaryDataStructure.getMax();
        if (maxDataNode == null) {
            Logger.log(ExecutionState.ERROR_NO_RECORDS);
        }else{
            Logger.log(maxDataNode.getKey(),ExecutionState.SUCCESS_MAX);
        }
    }
}
