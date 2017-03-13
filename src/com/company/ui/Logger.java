package com.company.ui;

import com.company.dataobjects.Customer;

import java.util.List;

/**
 * Created by bioel on 12-Mar-17.
 */
public class Logger {

    public static void log(Customer customer, ExecutionState executionState) {
        System.out.println(executionState.toString()+" "+customer.toString());
        System.out.println();
    }

    public static void log(int customerId, ExecutionState executionState) {
        System.out.println(executionState.toString()+" customer_id = " + customerId);
        System.out.println();
    }


    public static void log(ExecutionState executionState) {
        System.out.println(executionState.toString());
        System.out.println();
    }

    public static void log(List<Customer> customerList,ExecutionState executionState ) {
        System.out.println(executionState.toString());
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
        System.out.println();
    }
}
