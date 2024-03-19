package org.example.task1.command;

import org.example.task1.FileSystem;

import java.util.Scanner;

public class CommandHandler {

    public static final String UNKNOWN_COMMAND_MSG = "Unknown command. Available commands:\n<path>\nprint\nquit";

    private final CommandValidator validator;
    private final FileSystem ownerSystem;

    public CommandHandler(FileSystem owner) {
        this.ownerSystem = owner;
        validator = new CommandValidator();
    }

    public void start() {

        try (Scanner scanner = new Scanner(System.in)) {

            boolean quit = false;
            do {

                try {
                    String command = scanner.nextLine();
                    CommandType commandType = validator.validate(command);

                    if (commandType == CommandType.UNKNOWN) {
                        System.out.println(UNKNOWN_COMMAND_MSG);
                        continue;
                    }

                    switch (commandType) {
                        case QUIT -> quit = true;
                        case PRINT -> System.out.print(ownerSystem.getFileTree());
                        case PATH -> ownerSystem.add(command);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } while (!quit);
        }
    }
}
