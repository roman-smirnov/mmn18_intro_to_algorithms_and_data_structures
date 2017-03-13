package com.company.ui;

import com.company.dataobjects.Customer;

import java.util.List;

/**
 * Created by bioel on 12-Mar-17.
 */
public class Logger {
    public static void log(Customer customer, ExecutionState executionState) {
        System.out.println(executionState.toString()+" "+customer.toString());
    }
    public static void log(ExecutionState executionState) {
        System.out.println(executionState.toString());
    }
    public static void log(Customer customer) {
        System.out.println(customer.toString());
    }
}
