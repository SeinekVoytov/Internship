package org.example.systemcomponents;

import java.util.Objects;

public class File implements FileSystemComponent {

    private String name;

    public File(String name) {

        if (!name.matches("^[-_A-Za-z0-9]+\\.[a-z]+$")) {
            throw new IllegalArgumentException("Invalid file name specified");
        }

        this.name = name;
    }

    @Override
    public String toFileTreeString(int indentation) {
        return "\t".repeat(indentation) + getName() + "\n";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File file)) return false;
        return Objects.equals(name, file.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                '}';
    }
}
