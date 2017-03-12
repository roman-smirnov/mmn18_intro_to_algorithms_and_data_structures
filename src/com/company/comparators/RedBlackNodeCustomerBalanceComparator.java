package com.company.comparators;

import com.company.dataobjects.Customer;
import com.company.datastructures.tree.RedBlackNode;

import java.util.Comparator;

import static com.company.miscellaneous.Preconditions.checkNotNull;


/**
 * Compares Customer objects by customer ID
 */
public final class RedBlackNodeCustomerBalanceComparator implements Comparator<RedBlackNode<Customer>> {


    @Override
    public int compare(RedBlackNode<Customer> redBlackNode1, RedBlackNode<Customer> redBlackNode2) {
        checkNotNull(redBlackNode1);
        checkNotNull(redBlackNode2);

        return redBlackNode1.getKey().getBalance() - redBlackNode2.getKey().getBalance();
    }
}
