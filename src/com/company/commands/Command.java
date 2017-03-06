package com.company.commands;

import com.company.datastructures.DataStructure;

/**
 * the command interface abstracts over all our command objects
 */
public interface Command<T extends Comparable<T>>{
    ExecutionState execute(DataStructure<T> dataStructure);
}
