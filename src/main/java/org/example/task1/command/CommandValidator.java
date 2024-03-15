package org.example.task1.command;

import java.util.regex.Pattern;

public class CommandValidator {

    private final Pattern validPathCommandPattern =
            Pattern.compile("^/?root((/[-_A-Za-z0-9]+)+)?(((/[-_A-Za-z0-9]+)+)|([-_A-Za-z0-9]+\\.[a-z]+)+)?$");

    public CommandType validate(String command) {

        command = command.trim();

        if (command.equals("print")) {
            return CommandType.PRINT;
        }

        if (command.equals("quit")) {
            return CommandType.QUIT;
        }

        if (checkForValidPathCommand(command)) {
            return CommandType.PATH;
        }

        return CommandType.UNKNOWN;
    }

    private boolean checkForValidPathCommand(String command) {
        return validPathCommandPattern.matcher(command).matches();
    }
}
