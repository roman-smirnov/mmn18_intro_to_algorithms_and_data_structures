package com.company.commands;

import com.company.conditions.NegativeBalanceCondition;
import com.company.dataobjects.Customer;
import com.company.datastructures.DataStructure;
import com.company.ui.ExecutionState;
import com.company.ui.Logger;

import java.util.List;

/**
 * Created by bioel on 12-Mar-17.
 */
public class MinusCommand implements Command<Customer> {
    @Override
    public void execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure) {
        List<Customer> customerList = secondaryDataStructure.getAllKeys(new NegativeBalanceCondition());
        if (customerList.size() < 1) {
            Logger.log(ExecutionState.GET_NEGATIVE_BALANCES_ERROR_NO_RECORDS);
            return;
        }else{
            Logger.log(ExecutionState.GET_NEGATIVE_BALANCES_SUCCESS);
            for (Customer customer : customerList) {
                Logger.log(customer);
            }
        }
    }
}
