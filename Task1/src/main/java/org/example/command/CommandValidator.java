package org.example.command;

import java.util.regex.Pattern;

public class CommandValidator {

    private final Pattern validPathCommandPattern =
            Pattern.compile("^/?root((/[-_A-Za-z0-9]+)+)?(((/[-_A-Za-z0-9]+)+)|([-_A-Za-z0-9]+\\.[a-z]+)+)?$");

    public CommandType validate(String command) {

        command = command.trim();
        CommandType type;

        switch (command) {
            case "print" -> type = CommandType.PRINT;
            case "quit" -> type = CommandType.QUIT;
            default -> type = (checkForValidPathCommand(command) ? CommandType.PATH : CommandType.UNKNOWN);
        }

        return type;
    }

    private boolean checkForValidPathCommand(String command) {
        return validPathCommandPattern.matcher(command).matches();
    }
}
