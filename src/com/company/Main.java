package com.company;

import com.company.commands.Command;
import com.company.dataobjects.Customer;
import com.company.datastructures.DataStructure;
import com.company.datastructures.tree.RedBlackTree;
import com.company.utils.CommandParser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();
        DataStructure<Customer> dataStructure = new RedBlackTree<Customer>();
        while (true) {
            String commandString = scanner.nextLine();
            Command<Customer> command = commandParser.getCommand(commandString);
            command.execute(dataStructure);
        }
    }

}
