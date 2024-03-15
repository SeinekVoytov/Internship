package org.example.task1;

import org.example.task1.command.CommandType;
import org.example.task1.command.CommandValidator;
import org.example.task1.systemcomponents.File;
import org.example.task1.systemcomponents.FileSystemComponent;
import org.example.task1.systemcomponents.Folder;

import java.util.HashSet;
import java.util.Scanner;

public class FileSystem {

    public static final String UNKNOWN_COMMAND_MSG = "Unknown command. Available commands:\n<path>\nprint\nquit";

    private final Folder root;
    private final CommandValidator validator = new CommandValidator();

    private boolean quit;

    public FileSystem() {
        root = new Folder("root", new HashSet<>());
    }

    public void start() {

        try (Scanner scanner = new Scanner(System.in)) {

            quit = false;
            do {

                try {
                    String command = scanner.nextLine();
                    CommandType commandType = validator.validate(command);

                    if (commandType == CommandType.UNKNOWN) {
                        System.out.println(UNKNOWN_COMMAND_MSG);
                    }

                    switch (commandType) {
                        case QUIT -> quit = true;
                        case PRINT -> displayHierarchy();
                        case PATH -> traverseFileTree(command);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } while (!quit);
        }
    }

    private void traverseFileTree(String path) {

        if (path.charAt(0) == '/') {
            path = path.substring(1);
        }

        String[] pathParts = path.split("/");

        if (pathParts.length == 1) {    // /root case
            return;
        }

        Folder currentFolder = root;

        for (int i = 1; i < pathParts.length; i++) {

            String pathPart = pathParts[i];
            FileSystemComponent nextComponent = findNext(currentFolder, pathPart);

            if (nextComponent instanceof Folder) {
                currentFolder = (Folder) nextComponent;
                continue;
            }

            if (nextComponent instanceof File) {
                throw new IllegalArgumentException("File already exists");
            }

            if (isFile(pathPart)) {
                currentFolder.addNewComponent(new File(pathPart));
                break;
            }

            Folder newFolder = new Folder(pathPart);
            currentFolder.addNewComponent(newFolder);
            currentFolder = newFolder;
        }
    }

    private FileSystemComponent findNext(Folder folder, String targetName) {

        for (FileSystemComponent component : folder.getComponents()) {
            if (component.getName().equals(targetName)) {
                return component;
            }
        }

        return null;
    }

    private boolean isFile(String name) {
        return (name.matches("^.+\\.[a-z]+$"));
    }

    private void displayHierarchy() {
        root.display(0);
    }

    public boolean isQuit() {
        return quit;
    }
}
