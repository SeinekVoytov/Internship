package org.example.task1;

import org.example.task1.command.CommandHandler;
import org.example.task1.systemcomponents.File;
import org.example.task1.systemcomponents.FileSystemComponent;
import org.example.task1.systemcomponents.Folder;

import java.util.HashSet;

public class FileSystem {

    private final Folder root;
    private final CommandHandler receiver;

    public FileSystem() {
        root = new Folder("root", new HashSet<>());
        receiver = new CommandHandler(this);
    }

    public void start() {
        receiver.start();
    }

    public void add(String path) {
        traverseFileTree(path);
    }

    public String getFileTree() {
        return root.toFileTreeString(0);
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
                currentFolder.add(new File(pathPart));
                break;
            }

            Folder newFolder = new Folder(pathPart);
            currentFolder.add(newFolder);
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
}
