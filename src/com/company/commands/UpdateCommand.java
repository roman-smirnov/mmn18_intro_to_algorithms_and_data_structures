package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.miscellaneous.ExecutionState;
import com.company.dataobjects.Customer;

/**
 * Created by roman on 3/2/17.
 */
public class UpdateCommand implements Command<Customer>{
    private final String mName;
    private final int mId;
    private final int mCustomerId;
    private final int mBalance;

    public UpdateCommand(String name, int id, int customerId, int balance) {
        mName = name;
        mId = id;
        mCustomerId = customerId;
        mBalance = balance;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> dataStructure) {
        Customer tempCustomer = new Customer(mName, mId, mCustomerId, mBalance);
        Customer customer = dataStructure.find(tempCustomer);
        if (customer == null) {
            return ExecutionState.NOT_FOUND;
        }else{
            customer = new Customer(mName, mId, mCustomerId, mBalance + customer.getBalance());
            dataStructure.update(customer);
            return ExecutionState.SUCCESS;
        }
    }
}
