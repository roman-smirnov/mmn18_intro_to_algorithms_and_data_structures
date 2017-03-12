package com.company.commands;

import com.company.dataobjects.Customer;
import com.company.datastructures.DataStructure;

/**
 * Created by bioel on 12-Mar-17.
 */
public class MinusCommand implements Command<Customer> {
    @Override
    public void execute(DataStructure<Customer> mainDataStructure, DataStructure<Customer> secondaryDataStructure, DataStructure<Customer> tertiaryDataStructure) {
        // TODO implement logic to print out a list of all accounts with a negative balance
    }
}
