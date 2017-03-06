package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.dataobjects.Customer;

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
    public ExecutionState execute(DataStructure<Customer> dataStructure) {
        if(dataStructure.add(mCustomer)){
            System.out.println(ExecutionState.SUCCESS_ADD + " " + mCustomer.toString());
            return ExecutionState.SUCCESS_ADD;
        }else{
            System.out.println(ExecutionState.ERROR_ALREADY_EXISTS + " " + mCustomer.toString());
            return ExecutionState.ERROR_ALREADY_EXISTS;
        }


    }
}
