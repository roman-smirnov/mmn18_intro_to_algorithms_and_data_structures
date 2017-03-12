package com.company.ui;

import com.company.commands.ExecutionState;
import com.company.dataobjects.Customer;

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
}
