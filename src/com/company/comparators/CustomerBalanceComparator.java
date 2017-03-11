package com.company.comparators;

/**
 * Created by bioel on 11-Mar-17.
 */

import com.company.dataobjects.Customer;

import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;


/**
 * Compares Customer objects by balance
 */
public final class CustomerBalanceComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer customer1, Customer customer2) {
        checkNotNull(customer1);
        checkNotNull(customer2);

        return customer1.getBalance() - customer2.getBalance();
    }
}
