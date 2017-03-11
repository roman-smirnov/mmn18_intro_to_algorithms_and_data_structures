package com.company.commands;

import com.company.datastructures.DataNode;
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
        DataNode<Customer> customerDataNode = dataStructure.find(mCustomer);
        if (customerDataNode == null) {
            System.out.println(ExecutionState.ERROR_NOT_FOUND +" " + mCustomer);
            return ExecutionState.ERROR_NOT_FOUND;
        }else{
            Customer updatedCustomer = new Customer(mCustomer.getFirstName(),
                    mCustomer.getLastName(), mCustomer.getId(),
                    mCustomer.getCustomerId(), mCustomer.getBalance() + customerDataNode.getKey().getBalance());
            dataStructure.update(customerDataNode, updatedCustomer);

            System.out.println(ExecutionState.SUCCESS_UPDATE+" " + customerDataNode);
            return ExecutionState.SUCCESS_UPDATE;
        }
    }
}
