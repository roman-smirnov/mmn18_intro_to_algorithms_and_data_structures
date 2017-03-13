package com.company.commands;

import com.company.dataobjects.Customer;
import com.company.datastructures.DataNode;
import com.company.datastructures.DataStructure;
import com.company.ui.ExecutionState;
import com.company.ui.Logger;

/**
 *  the max command
 */
public final class MaxCommand implements Command<Customer>{

    @Override
    public void execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure ) {
        DataNode<Customer> maxDataNode = secondaryDataStructure.getMax();
        if (maxDataNode == null) {
            Logger.log(ExecutionState.FIND_MAX_ERROR_NO_RECORDS);
        }else{
            Logger.log(maxDataNode.getKey(),ExecutionState.FIND_MAX_SUCCESS);
        }
    }
}
