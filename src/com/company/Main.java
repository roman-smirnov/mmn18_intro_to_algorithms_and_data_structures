package com.company;

import com.company.commands.Command;
import com.company.dataobjects.Customer;
import com.company.datastructures.DataStructure;
import com.company.datastructures.tree.RedBlackTree;
import com.company.utils.CommandParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser();
        DataStructure<Customer> dataStructure = new RedBlackTree<Customer>();
        for (String commandString : getTestCommandList()) {
            Command<Customer> command = commandParser.getCommand(commandString);
            if (command != null) {
                command.execute(dataStructure);
            }
        }

    }

    private static List<String> getTestCommandList() {
        List<String> commandList = new ArrayList<>(20);

        // add stuff
        commandList.add("+ r s 1 1 0");
        commandList.add("+ r s 2 2 0");
        commandList.add("+ r s 3 3 0");
        commandList.add("+ r s 4 4 0");
        commandList.add("+ r s 5 5 0");
        commandList.add("+ r s 6 6 0");
        commandList.add("+ r s 0 0 0");
        commandList.add("+ r s 7 7 0");
        commandList.add("+ r s -1 -1 0");

        //delete stuff
        commandList.add("- 1");
        commandList.add("- 2");
        commandList.add("- 3");
        commandList.add("- 4");
        commandList.add("- 5");
        commandList.add("- 6");
        commandList.add("- 0");
        commandList.add("- 7");
        commandList.add("- -1");

        return commandList;
    }


    private void handleUserInput() {
        CommandParser commandParser = new CommandParser();
        DataStructure<Customer> dataStructure = new RedBlackTree<Customer>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandString = scanner.nextLine();
            Command<Customer> command = commandParser.getCommand(commandString);
            if (command != null) {
                command.execute(dataStructure);
            }
        }
    }

}
