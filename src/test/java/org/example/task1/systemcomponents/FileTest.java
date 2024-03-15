package org.example.task1.systemcomponents;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    @Test
    public void emptyFileNameShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new File(""));
    }

    @Test
    public void fileNameWithoutExtensionShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new File("file"));
    }

    @Test
    public void fileNameWithEmptyExtensionShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new File("file."));
    }

    @Test
    public void illegalCharactersInExtensionForFileShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new File("file.t_t"));
    }

    @Test
    public void invalidCharacterInFileNameShouldThrowExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new File("$file.txt"));
    }

    @Test
    public void fileNameWithUnderscoresDashesShouldNotThrowException() {
        try {
            new File("a-b.txt");
            new File("a_b.xml");
        }
        catch (IllegalArgumentException exc) {
            Assertions.fail("File name should be able to contain underscores and dashes");
        }
    }
}