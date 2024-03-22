package org.example.systemcomponents;

public interface FileSystemComponent {
    String getName();
    void setName(String newName);
    String toFileTreeString(int indentation);
}
