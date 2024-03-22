package org.example.systemcomponents;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FolderTest {

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenEmptyFolderNameSpecified() {
        assertThrows(IllegalArgumentException.class, () -> new Folder(""));
    }

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenFolderNameWithExtensionSpecified() {
        assertThrows(IllegalArgumentException.class, () -> new Folder("folder.xls"));
    }

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenFolderNameContainEmptyExtension() {
        assertThrows(IllegalArgumentException.class, () -> new Folder("folder."));
    }

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenFolderNameContainIllegalCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Folder("folder$"));
    }
}