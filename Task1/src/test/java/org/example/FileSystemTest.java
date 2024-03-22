package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
class FileSystemTest {

    private final FileSystem fileSystem = new FileSystem();

    @Test
    void getFileTree_ShouldReturnRootDir_WhenNothingAdded() {
        assertEquals("root/\n", fileSystem.getFileTree());
    }

    @Test
    void getFileTree_ShouldReturnOneFilePath_WhenOneFileAdded() {
        fileSystem.add("root/file.txt");
        assertEquals("root/\n\tfile.txt\n", fileSystem.getFileTree());
    }

    @Test
    void getFileTree_ShouldReturnValidPath_WhenOneDirectoryAdded() {
        fileSystem.add("root/folder");
        assertEquals("root/\n\tfolder/\n", fileSystem.getFileTree());
    }

    @Test
    void getFileTree_ShouldReturnValidPath_WhenInnerFileAndDirectoryAdded() {
        fileSystem.add("/root/folder/file.xml");
        assertEquals("root/\n\tfolder/\n\t\tfile.xml\n", fileSystem.getFileTree());
    }

    @Test
    void getFileTree_ShouldReturnValidPath_WhenTwoInnerDirectoriesAdded() {
        fileSystem.add("root/folder1");
        fileSystem.add("/root/folder2");
        assertEquals("root/\n\tfolder1/\n\tfolder2/\n", fileSystem.getFileTree());
    }

    @Test
    void getFileTree_ShouldReturnValidPath_WhenTwoInnerFilesAdded() {
        fileSystem.add("root/file1.txt");
        fileSystem.add("/root/file2.xml");
        assertEquals("root/\n\tfile1.txt\n\tfile2.xml\n", fileSystem.getFileTree());
    }

    @Test
    void getFileTree_ShouldReturnRootDir_WhenExistedFolderAdded() {
        fileSystem.add("/root");
        assertEquals("root/\n", fileSystem.getFileTree());
    }

    @Test
    void add_ShouldTrowIllegalArgumentException_WhenFileAlreadyExist() {
        fileSystem.add("/root/file.iml");
        assertThrows(IllegalArgumentException.class, () -> fileSystem.add("/root/file.iml"));
    }
}