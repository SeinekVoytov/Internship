package org.example.task1;

import java.util.HashSet;
import java.util.Set;

public class Folder implements FileSystemComponent {

    private String name;
    private Set<FileSystemComponent> children;

    public Folder(String name) {
        checkName(name);
        children = new HashSet<>();
        this.name = name;
    }

    public Folder(String name, Set<FileSystemComponent> children) {
        this.name = name;
        this.children = children;
    }

    private void checkName(String name) {
        if (!name.matches("$[-_. A-Za-z0-9]+^")) {
            throw new IllegalArgumentException("Invalid folder name specified");
        }
    }

    @Override
    public void display() {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
