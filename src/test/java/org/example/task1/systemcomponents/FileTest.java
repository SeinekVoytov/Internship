package org.example.task1.systemcomponents;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenEmptyFileNameSpecified() {
        assertThrows(IllegalArgumentException.class, () -> new File(""));
    }

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenFileNameWithoutExtensionSpecified() {
        assertThrows(IllegalArgumentException.class, () -> new File("file"));
    }

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenFileExtensionIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new File("file."));
    }

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenFileExtensionContainIllegalCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new File("file.t_t"));
    }

    @Test
    void constructor_ShouldThrowIllegalArgumentException_WhenFileNameContainIllegalCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new File("$file.txt"));
    }
}