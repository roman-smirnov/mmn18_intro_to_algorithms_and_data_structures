package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.miscellaneous.ExecutionState;
import com.company.dataobjects.Customer;

/**
 * Created by roman on 3/2/17.
 */
public class DeleteCommand implements Command<Customer> {
    private final int mCustomerId;

    public DeleteCommand(int id) {
        mCustomerId = id;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> dataStructure) {
        Customer tempCustomer = new Customer("", "", 0, mCustomerId, 0);
        Customer customer = dataStructure.find(tempCustomer);
        if (customer == null) {
            return ExecutionState.NOT_FOUND;
        } else if (customer.getBalance() != 0) {
            System.out.println(ExecutionState.BALANCE_NOT_ZERO + " " + customer.toString());
            return ExecutionState.BALANCE_NOT_ZERO;
        } else {
            dataStructure.delete(customer);
            System.out.println(ExecutionState.SUCCESS + " " + customer.toString());
            return ExecutionState.SUCCESS;
        }
    }
}
