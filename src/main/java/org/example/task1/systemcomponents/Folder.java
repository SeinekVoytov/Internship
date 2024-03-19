package org.example.task1.systemcomponents;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Folder implements FileSystemComponent {

    private String name;
    private final Set<FileSystemComponent> components;

    public Folder(String name) {
        checkName(name);
        components = new HashSet<>();
        this.name = name;
    }

    public Folder(String name, Set<FileSystemComponent> components) {
        this.name = name;
        this.components = components;
    }

    private void checkName(String name) {
        if (!name.matches("^[-_A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("Invalid folder name specified");
        }
    }

    public void add(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public String toFileTreeString(int indentation) {

        StringBuilder builder = new StringBuilder("\t".repeat(indentation));
        builder.append(getName()).append("/\n");
        for (FileSystemComponent component : components) {
            builder.append(component.toFileTreeString(indentation + 1));
        }

        return builder.toString();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Set<FileSystemComponent> getComponents() {
        return components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Folder folder)) return false;
        return Objects.equals(name, folder.name) && Objects.equals(components, folder.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, components);
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                ", children=" + components +
                '}';
    }
}
