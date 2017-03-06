package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.dataobjects.Customer;

/**
 *  the delete command
 */
public final class DeleteCommand implements Command<Customer> {
    private final int mCustomerId;

    public DeleteCommand(int id) {
        mCustomerId = id;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> dataStructure) {
        Customer tempCustomer = new Customer("", "", 0, mCustomerId, 0);
        Customer customer = dataStructure.find(tempCustomer);
        if (customer == null) {
            System.out.println(ExecutionState.ERROR_NOT_FOUND + " customer with customerId "+mCustomerId + " not found");
            return ExecutionState.ERROR_NOT_FOUND;
        } else if (customer.getBalance() != 0) {
            System.out.println(ExecutionState.ERROR_BALANCE_NOT_ZERO + " " + customer.toString());
            return ExecutionState.ERROR_BALANCE_NOT_ZERO;
        } else {
            dataStructure.delete(customer);
            System.out.println(ExecutionState.SUCCESS_DELETE + " " + customer.toString());
            return ExecutionState.SUCCESS_DELETE;
        }
    }
}
