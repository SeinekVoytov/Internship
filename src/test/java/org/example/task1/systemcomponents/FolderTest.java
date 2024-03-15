package org.example.task1.systemcomponents;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FolderTest {

    @Test
    public void emptyFolderNameShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Folder(""));
    }

    @Test
    public void folderNameWithExtensionShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Folder("folder.xls"));
    }

    @Test
    public void folderNameWithEmptyExtensionShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Folder("folder."));
    }

    @Test
    public void illegalCharactersInFolderNameShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Folder("folder$"));
    }

    @Test
    public void folderNameWithUnderscoresDashesShouldNotThrowException() {
        try {
            new Folder("a-b");
            new Folder("a_b");
        }
        catch (IllegalArgumentException exc) {
            Assertions.fail("Folder name should be able to contain underscores and dashes");
        }
    }
}