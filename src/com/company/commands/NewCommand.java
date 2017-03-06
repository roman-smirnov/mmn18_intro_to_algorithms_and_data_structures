package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.miscellaneous.ExecutionState;
import com.company.dataobjects.Customer;

import static com.company.utils.Preconditions.checkNotNull;

/**
 * Created by roman on 3/2/17.
 */
public class NewCommand implements Command<Customer>{
    private final Customer mCustomer;

    public NewCommand(Customer customer) {
        checkNotNull(customer);
        mCustomer = customer;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> dataStructure) {
        if(dataStructure.add(mCustomer)){
            System.out.println(ExecutionState.SUCCESS + " " + mCustomer.toString());
            return ExecutionState.SUCCESS;
        }else{
            System.out.println(ExecutionState.ALREADY_EXISTS + " " + mCustomer.toString());
            return ExecutionState.ALREADY_EXISTS;
        }


    }
}
