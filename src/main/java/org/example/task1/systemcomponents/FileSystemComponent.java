package org.example.task1.systemcomponents;

public interface FileSystemComponent {
    String getName();
    void setName(String newName);
    String toFileTreeString(int indentation);
}
