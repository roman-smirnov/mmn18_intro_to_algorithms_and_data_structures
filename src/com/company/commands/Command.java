package com.company.commands;

import com.company.datastructures.DataStructure;
import com.company.miscellaneous.ExecutionState;

/**
 * Created by roman on 3/4/17.
 */
public interface Command<T extends Comparable<T>>{
    ExecutionState execute(DataStructure<T> dataStructure);
}
