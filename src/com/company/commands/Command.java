package com.company.commands;

import com.company.datastructures.DataStructure;


/**
 * the command interface abstracts over all our command objects
 */
public interface Command<K>{

    void execute(DataStructure<K> mainDataStructure,DataStructure<K> secondaryDataStructure, DataStructure<K> tertiaryDataStructure);

}
