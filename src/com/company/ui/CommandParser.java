package com.company.ui;

import com.company.commands.*;
import com.company.dataobjects.Customer;

import java.util.Arrays;
import java.util.List;

import static com.company.ui.Constants.*;
import static com.company.miscellaneous.Preconditions.checkNotNull;

/**
 * This class turns user input string into command objects
 */
public class CommandParser {

    private enum CommandType{ NEW, UPDATE, DELETE, GET, MAX ,MINUS, INVALID}

    /**
     * Get the command object
     * may returns
     * @param command
     * @return
     */
    public Command<Customer> getCommand(String command){
        checkNotNull(command);
        //parse the strings into a string list using a regular expression
        List<String> parsedCommand = parseCommand(command);
        //figure out what kind of command is this
        CommandType commandType = getCommandType(parsedCommand);
        //extract the data from the string list
        switch (commandType) {
            case NEW:
                return generateNewCommand(parsedCommand);
            case UPDATE:
                return generateUpdateCommand(parsedCommand);
            case DELETE:
                return generateDeleteCommand(parsedCommand);
            case GET:
                return generateGetCommand(parsedCommand);
            case MAX:
                return generateMaxCommand(parsedCommand);
            case MINUS:
                return generateMinusCommand(parsedCommand);
            default:
                System.out.println(CommandType.INVALID+ " You've entered an invalid command");
                return null;
        }
    }




    /**
     * given a valid string list for a new command, generates a NewCommand
     * @param parsedCommand
     * @return
     */
    private NewCommand generateNewCommand(List<String> parsedCommand) {
        checkNotNull(parsedCommand);
        String firstName = parsedCommand.get(1);
        String lastName = parsedCommand.get(2);
        int id = Integer.parseInt(parsedCommand.get(3));
        int customerId = Integer.parseInt(parsedCommand.get(4));
        int balance = Integer.parseInt(parsedCommand.get(5));
        return new NewCommand(new Customer(firstName,lastName,id, customerId, balance));
    }

    /**
     * given a valid string list for a delete command, generates a DeleteCommand
     * @param parsedCommand
     * @return
     */
    private DeleteCommand generateDeleteCommand(List<String> parsedCommand) {
        int customerId = Integer.parseInt(parsedCommand.get(1));
        Customer customer = new Customer("", "", -1, customerId, 0);
        return new DeleteCommand(customer);
    }


    /**
     * given a valid string list for an update command, generates a UpdateCommand
     * @param parsedCommand
     * @return
     */
    private UpdateCommand generateUpdateCommand(List<String> parsedCommand) {
        checkNotNull(parsedCommand);
        String firstName = parsedCommand.get(0);
        String lastName = parsedCommand.get(1);
        int id = -1;
        int customerId = Integer.parseInt(parsedCommand.get(2));
        int balance = Integer.parseInt(parsedCommand.get(3));
        return new UpdateCommand(new Customer(firstName,lastName,id, customerId, balance));
    }

    /**
     * given a valid string list for an GetCommand, generates a GetCommand
     * @param parsedCommand
     * @return
     */
    private Command<Customer> generateGetCommand(List<String> parsedCommand) {
        int customerId = Integer.parseInt(parsedCommand.get(1));
        Customer customer = new Customer("", "", -1, customerId, 0);
        return new GetCommand(customer);
    }

    /**
     * given a valid string list for an MaxCommand, generates a MaxCommand
     * @param parsedCommand
     * @return
     */
    private Command<Customer> generateMaxCommand(List<String> parsedCommand) {
        checkNotNull(parsedCommand);
        return new MaxCommand();
    }

    /**
     * given a valid string list for an MaxCommand, generates a MaxCommand
     * @param parsedCommand
     * @return
     */
    private Command<Customer> generateMinusCommand(List<String> parsedCommand) {
        checkNotNull(parsedCommand);
        return new MinusCommand();
    }

    /**
     * get the type of the command
     * @param parsedCommand
     * @return
     */
    private CommandType getCommandType( List<String> parsedCommand) {
        checkNotNull(parsedCommand);
        if (validateNewClientCommand(parsedCommand)) {
            return CommandType.NEW;
        } else if (validateUpdateClientCommand(parsedCommand)) {
            return CommandType.UPDATE;
        } else if (validateDeleteClientCommand(parsedCommand)) {
            return CommandType.DELETE;
        } else if (validateGetCommand(parsedCommand)) {
            return CommandType.GET;
        } else if (validateMaxCommand(parsedCommand)) {
            return CommandType.MAX;
        } else if (validateMinusCommand(parsedCommand)) {
            return CommandType.MINUS;
        } else {
            return CommandType.INVALID;
        }
    }

    /**
     * splits the command input string into discrete words seperated by spaces
     * @param command
     * @return
     */
    private List<String> parseCommand(String command) {
        checkNotNull(command);
        return Arrays.asList(command.trim().split("\\s+"));
    }

    /**
     * this methods check for the following:
     * valid number of parameters
     * valid command start symbol
     * valid integers in command
     * @param cmd the parsed command string list
     * @return true if the command is valid, false otherwise
     */
    private boolean validateNewClientCommand(List<String> cmd) {
        return cmd.size() == NEW_CLIENT_COMMAND_LENGTH
                && cmd.get(0).equals(NEW_CLIENT_CMD_START_SYMBOL)
                && checkStringsAreIntegers(cmd, 3, 5);
    }

    /**
     * this methods check for the following:
     * valid number of parameters
     * valid command start symbol
     * valid integers in command
     * @param cmd command input string list
     * @return true if the command is valid, false otherwise
     */
    private boolean validateUpdateClientCommand(List<String> cmd) {
        return cmd.size() == UPDATE_CLIENT_COMMAND_LENGTH
                && checkStringsAreIntegers(cmd, 2, 3);
    }

    /**
     * this methods check for the following:
     * valid number of parameters
     * valid command start symbol
     * @param cmd command input string list
     * @return true if the command is valid, false otherwise
     */
    private boolean validateDeleteClientCommand(List<String> cmd) {
        return cmd.size() == DELETE_CLINET_COMMAND_LENGTH
                && cmd.get(0).equals(DELETE_CLIENT_COMMAND_START_SYMBOL)
                && isInteger(cmd.get(1));
    }

    /**
     * this methods check for the following:
     * valid number of parameters
     * valid command start symbol
     * @param cmd command input string list
     * @return true if the command is valid, false otherwise
     */
    private boolean validateGetCommand(List<String> cmd){
        return cmd.size() == QUERY_COMMAND_LENGTH
                && cmd.get(0).equals(QUERY_COMMAND_START_SYMBOL)
                && isInteger(cmd.get(1));
    }

    /**
     * this methods check for the following:
     * valid number of parameters
     * valid command start symbol
     * @param cmd command input string list
     * @return true if the command is valid, false otherwise
     */
    private boolean validateMaxCommand(List<String> cmd){
        return cmd.size() == QUERY_COMMAND_LENGTH
                && cmd.get(0).equals(QUERY_COMMAND_START_SYMBOL)
                && cmd.get(1).equals(QUERY_COMMAND_MAX);
    }

    /**
     * this methods check for the following:
     * valid number of parameters
     * valid command start symbol
     * @param cmd command input string
     * @return true if the command is valid, false otherwise
     */
    private boolean validateMinusCommand(List<String> cmd){
        return cmd.size() == QUERY_COMMAND_LENGTH
                && cmd.get(0).equals(QUERY_COMMAND_START_SYMBOL)
                && cmd.get(1).equals(QUERY_COMMAND_MINUS);
    }

    /**
     * check all strings from s to e are integers
     * @param cmd
     * @param s
     * @param e
     * @return
     */
    private boolean checkStringsAreIntegers(List<String> cmd, int s, int e) {
        for (int i = s; i <= e; i++) {
            if (!isInteger(cmd.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * check whether the string is an integer
     * @param s
     * @return
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // we only get here if it's really and integer
        return true;
    }


}
