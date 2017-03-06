package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.dataobjects.Customer;

import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 *  the update command
 */
public final class UpdateCommand implements Command<Customer>{
    private final Customer mCustomer;

    public UpdateCommand(Customer customer) {
        checkNotNull(customer);
        mCustomer = customer;
    }

    @Override
    public ExecutionState execute(DataStructure<Customer> dataStructure) {
        checkNotNull(dataStructure);
        Customer customer = dataStructure.find(mCustomer);
        if (customer == null) {
            System.out.println(ExecutionState.ERROR_NOT_FOUND +" " + mCustomer);
            return ExecutionState.ERROR_NOT_FOUND;
        }else{
            customer = new Customer(mCustomer.getFirstName(),
                    mCustomer.getLastName(), mCustomer.getId(),
                    mCustomer.getCustomerId(), mCustomer.getBalance() + customer.getBalance());
            dataStructure.update(customer);
            System.out.println(ExecutionState.SUCCESS_UPDATE+" " + customer);
            return ExecutionState.SUCCESS_UPDATE;
        }
    }
}
