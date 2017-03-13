package com.company.conditions;

import com.company.dataobjects.Customer;

import javax.swing.*;

/**
 * a condition to compare objects against
 *  returns true when a customer has a negative balance
 */
public class NegativeBalanceCondition implements Condition<Customer> {
    @Override
    public boolean isConditionMet(Customer key1) {
        return key1.getBalance() < 0;
    }
}
