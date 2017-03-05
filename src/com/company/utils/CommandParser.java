package com.company.utils;

import com.company.commands.Command;
import com.company.commands.DeleteCommand;
import com.company.commands.NewCommand;
import com.company.commands.UpdateCommand;
import com.company.dataobjects.Customer;

import java.util.Arrays;
import java.util.List;

import static com.company.miscellaneous.Constants.*;

/**
 * Created by roman on 3/2/17.
 */
public class CommandParser {

    private enum CommandType{ NEW, UPDATE, DELETE, MAX,MINUS, INVALID}


    public Command<Customer> getCommand(String command){
        List<String> parsedCommand = parseCommand(command);
        CommandType commandType = getCommandType(parsedCommand);
        switch (commandType) {
            case NEW:
                return generateNewCommand(parsedCommand);
            case UPDATE:
                return generateUpdateCommand(parsedCommand);
            case DELETE:
                return generateDeleteCommand(parsedCommand);
            case MAX:
                System.out.println(CommandType.MAX.name() + "NOT YET IMPLEMENTED");
                return null;
            case MINUS:
                System.out.println(CommandType.MINUS.name() + "NOT YET IMPLEMENTED");
                return null;
            default:
                System.out.println(CommandType.INVALID.name()+ " You've entered an invalid command");
                return null;
        }
    }


    private NewCommand generateNewCommand(List<String> parsedCommand) {
        String name = parsedCommand.get(1);
        int id = Integer.getInteger(parsedCommand.get(2));
        int customerId = Integer.getInteger(parsedCommand.get(3));
        int balance = Integer.getInteger(parsedCommand.get(4));
        return new NewCommand(name, id, customerId, balance);
    }

    private DeleteCommand generateDeleteCommand(List<String> parsedCommand) {
        int customerId = Integer.getInteger(parsedCommand.get(1));
        return new DeleteCommand(customerId);
    }

    private UpdateCommand generateUpdateCommand(List<String> parsedCommand) {
        String name = parsedCommand.get(0);
        int id = Integer.getInteger(parsedCommand.get(1));
        int customerId = Integer.getInteger(parsedCommand.get(2));
        int balance = Integer.getInteger(parsedCommand.get(3));
        return new UpdateCommand(name, id, customerId, balance);
    }





    /**
     * get the type of the command
     * @param parsedCommand
     * @return
     */
    private CommandType getCommandType( List<String> parsedCommand) {
        if (validateNewClientCommand(parsedCommand)) {
            return CommandType.NEW;
        } else if (validateUpdateClientCommand(parsedCommand)) {
            return CommandType.UPDATE;
        } else if (validateDeleteClientCommand(parsedCommand)) {
            return CommandType.DELETE;
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
        return Arrays.asList(command.split("\\s+"));
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
