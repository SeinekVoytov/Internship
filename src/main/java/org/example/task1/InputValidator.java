package org.example.task1;

import java.util.regex.Pattern;

public class InputValidator {

    private final Pattern validPathCommandPattern =
            Pattern.compile("root((/[-_A-Za-z0-9]+)+)?(/[-._A-Za-z0-9]+)+");

    public CommandType validate(String command) {

        command = command.trim();

        if (command.isEmpty() || command.isBlank()) {
            System.err.println("Command cannot be empty or blank");
            return CommandType.UNKNOWN;
        }

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
