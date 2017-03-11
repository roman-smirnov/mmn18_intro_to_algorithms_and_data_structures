package com.company.comparators;

import com.company.dataobjects.Customer;

import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;


/**
 * Compares Customer objects by customer ID
 */
public final class CustomerIdComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer customer1, Customer customer2) {
        checkNotNull(customer1);
        checkNotNull(customer2);

        return customer1.getCustomerId() - customer2.getCustomerId();
    }
}
