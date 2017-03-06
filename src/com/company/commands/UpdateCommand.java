package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.miscellaneous.ExecutionState;
import com.company.dataobjects.Customer;

import static com.company.utils.Preconditions.checkNotNull;

/**
 * Created by roman on 3/2/17.
 */
public class UpdateCommand implements Command<Customer>{
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
            System.out.println(ExecutionState.NOT_FOUND+" " + mCustomer);
            return ExecutionState.NOT_FOUND;
        }else{
            customer = new Customer(mCustomer.getFirstName(),
                    mCustomer.getLastName(), mCustomer.getId(),
                    mCustomer.getCustomerId(), mCustomer.getBalance() + customer.getBalance());
            dataStructure.update(customer);
            System.out.println(ExecutionState.SUCCESS+" " + customer);
            return ExecutionState.SUCCESS;
        }
    }
}
