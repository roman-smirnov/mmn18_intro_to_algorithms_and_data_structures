package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.miscellaneous.ExecutionState;
import com.company.dataobjects.Customer;

/**
 * Created by roman on 3/2/17.
 */
public class NewCommand implements Command<Customer>{
    private final String mName;
    private final int mId;
    private final int mCustomerId;
    private final int mBalance;

    public NewCommand(String name, int id, int customerId, int balance) {
        mName = name;
        mId = id;
        mCustomerId = customerId;
        mBalance = balance;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> dataStructure) {
        Customer customer = new Customer(mName, mId, mCustomerId, mBalance);
        if(dataStructure.add(customer)){
            return ExecutionState.SUCCESS;
        }else{
            return ExecutionState.ALREADY_EXISTS;
        }


    }
}
