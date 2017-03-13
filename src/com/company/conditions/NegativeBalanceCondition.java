package com.company.conditions;

import com.company.dataobjects.Customer;

import javax.swing.*;

/**
 * Created by bioel on 13-Mar-17.
 */
public class NegativeBalanceCondition implements Condition<Customer> {
    @Override
    public boolean isConditionMet(Customer key1) {
        return key1.getBalance() < 0;
    }
}
