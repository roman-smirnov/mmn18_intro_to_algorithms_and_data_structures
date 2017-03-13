package com.company.commands;

import com.company.datastructures.DataStructure;


/**
 * the command interface abstracts over all our command objects
 */
public interface Command<K>{

    /**
     * execute a command on the datastracture
     * @param mainDataStructure
     * @param secondaryDataStructure
     * @param tertiaryDataStructure
     */
    void execute(DataStructure<K> mainDataStructure,DataStructure<K> secondaryDataStructure, DataStructure<K> tertiaryDataStructure);

}
