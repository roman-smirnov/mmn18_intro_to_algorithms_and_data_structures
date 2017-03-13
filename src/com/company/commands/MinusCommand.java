package com.company.commands;

import com.company.conditions.NegativeBalanceCondition;
import com.company.dataobjects.Customer;
import com.company.datastructures.DataStructure;
import com.company.ui.ExecutionState;
import com.company.ui.Logger;

import java.util.List;

/**
 * the minus command
 */
public class MinusCommand implements Command<Customer> {
    @Override
    public void execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure) {
        List<Customer> customerList = tertiaryDataStructure.getAllKeys(new NegativeBalanceCondition());
        if (customerList.size() < 1) {
            Logger.log(ExecutionState.NEGATIVE_BALANCES_ERROR_NO_RECORDS);
            return;
        }else{
            Logger.log(customerList,ExecutionState.NEGATIVE_BALANCES_SUCCESS);
        }
    }
}
