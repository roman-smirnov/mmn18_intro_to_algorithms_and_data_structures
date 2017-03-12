package com.company.comparators;

import com.company.dataobjects.Customer;

import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;


/**
 * Compares Customer objects by customer ID
 */
public final class CustomerIdComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer c1, Customer c2) {
        checkNotNull(c1);
        checkNotNull(c2);

        return c1.getCustomerId() - c2.getCustomerId();
    }
}

