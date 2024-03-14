package org.example.task1;

public class File implements FileSystemComponent {

    private String name;

    public File(String name) {

        if (!name.matches("$[-_. A-Za-z0-9]+\\.[a-z]+^")) {
            throw new IllegalArgumentException("Invalid file name specified");
        }

        this.name = name;
    }

    @Override
    public void display() {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
