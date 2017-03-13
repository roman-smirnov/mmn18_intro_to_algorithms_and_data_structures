package com.company;

import com.company.commands.Command;
import com.company.dataobjects.Customer;
import com.company.ui.CommandParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MMN18 for intro to algorithms and data structures 2017a
 * by Amit Nissan and Roman Smirnov
 */
public class Main {

    /**
     * the main method
     *
     * @param args
     */
    public static void main(String[] args) {
//        handleUserInput() // uncomment to give commands manually
        runTests();
    }


    /**
     * integration tests get called from here
     */
    private static void runTests() {
        //command parser converts user input strings into command objects
        CommandParser commandParser = new CommandParser();
        // the datastructure interface abstracts over all our data structures
        DataStructureManager dataStructureManager = new DataStructureManager();
        for (String commandString : getTestCommandList()) {
            //the command interface abstracts over all command classes
            Command<Customer> command = commandParser.getCommand(commandString);
            dataStructureManager.executeCommand(command);
        }
    }


    /**
     * call this to input commands manually
     */
    private static void handleUserInput() {
        CommandParser commandParser = new CommandParser();
        DataStructureManager dataStructureManager = new DataStructureManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandString = scanner.nextLine();
            Command<Customer> command = commandParser.getCommand(commandString);
            dataStructureManager.executeCommand(command);
        }
    }

    /**
     * put your tests here
     *
     * @return
     */
    private static List<String> getTestCommandList() {
        List<String> commandList = new ArrayList<>(20);

        // add stuff
        commandList.add("+ r s 1 1 1");
        commandList.add("+ r s 2 2 1");
        commandList.add("+ r s 3 3 1");
        commandList.add("+ r s 4 4 1");
        commandList.add("+ r s 5 5 1");
        commandList.add("+ r s 6 6 1");
        commandList.add("+ r s 0 0 5");
        commandList.add("+ r s 7 7 1");
        commandList.add("+ r s -1 -1 1");
        commandList.add("+ r s -1 -10 -10");
        commandList.add("+ r s -1 -11 -11");


        // get specific persons
        commandList.add("? 1");
        commandList.add("? -1");
        commandList.add("? 2000");
        commandList.add("? 0");


        // get maximum balance
        commandList.add("? MAX");

        // update stuff
        commandList.add("r   s 1   1 -1");
        commandList.add("    r s 2 2 -1");
        commandList.add(" r s 3 3 -1");
        commandList.add(" r s   4 4 -1");
        commandList.add(" r s 5 5 -1");
        commandList.add(" r s   6 6 -1");
        commandList.add(" r s 0 0 -1");
        commandList.add(" r  s 7   7 -1");
        commandList.add(" r s -1 -1 -1");

        //printout all the negative balances
        commandList.add("? MINUS");

        // get maximum balance
        commandList.add("? MAX");

        // delete stuff
        commandList.add("- 1    ");
        commandList.add("- 2");

        // get specific persons
        commandList.add("? 1");
        commandList.add("? 2");
        commandList.add("? 3");
        commandList.add("? 4");

        commandList.add("-   3");
        commandList.add("- 4");
        commandList.add("- 5");
        commandList.add("- 6");
        commandList.add("- 0 ");
        commandList.add("- 7");
        commandList.add("- -1");

        return commandList;
    }

}
