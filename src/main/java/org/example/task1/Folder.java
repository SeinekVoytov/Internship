package org.example.task1;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Folder implements FileSystemComponent {

    private String name;
    private final Set<FileSystemComponent> children;

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

    public void addNewComponent(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void display(int indentation) {
        System.out.println(" ".repeat(indentation) + getName());
        for (FileSystemComponent component : children) {
            component.display(indentation + 1);
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Folder)) return false;
        Folder folder = (Folder) o;
        return Objects.equals(name, folder.name) && Objects.equals(children, folder.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children);
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
