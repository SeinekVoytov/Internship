package org.example.task1;

import org.example.task1.systemcomponents.File;
import org.example.task1.systemcomponents.FileSystemComponent;
import org.example.task1.systemcomponents.Folder;

import java.util.HashSet;
import java.util.Scanner;

public class FileSystem {

    private final Folder root;

    private final InputValidator validator = new InputValidator();

    public FileSystem() {
        root = new Folder("root", new HashSet<>());
    }

    public void start() {

        try (Scanner scanner = new Scanner(System.in)) {

            boolean quit = false;
            do {
                String command = scanner.nextLine();
                CommandType commandType = validator.validate(command);

                if (commandType == CommandType.UNKNOWN) {
                    System.err.println("Unknown command");
                }

                switch (commandType) {
                    case QUIT -> quit = true;
                    case PRINT -> displayHierarchy();
                    case PATH -> traverseFileTree(command);
                }

            } while (!quit);
        }
    }

    private void traverseFileTree(String path) {

        String[] pathParts = path.split("/");

        if (pathParts.length == 1) {
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
                break;
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

    private boolean isFile(String name) {
        return name.matches("^.+\\.[a-z]+$");
    }

    private FileSystemComponent findNext(Folder folder, String targetName) {

        for (FileSystemComponent component : folder.getComponents()) {
            if (component.getName().equals(targetName)) {
                return component;
            }
        }

        return null;
    }

    private void displayHierarchy() {
        root.display(0);
    }
}
