package org.example.task1.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandValidatorTest {

    private final CommandValidator validator = new CommandValidator();

    @Test
    public void emptyCommandValidationShouldReturnUnknownCommandTypeTest() {
        assertEquals(CommandType.UNKNOWN, validator.validate(""));
    }

    @Test
    public void blankCommandValidationShouldReturnUnknownCommandTypeTest() {
        assertEquals(CommandType.UNKNOWN, validator.validate("\t"));
    }

    @Test
    public void validCommandWithTrailingSpacesShouldReturnValidCommandTypeTest() {
        assertEquals(CommandType.QUIT, validator.validate("  quit    "));
        assertEquals(CommandType.PATH, validator.validate("\troot\t"));
        assertEquals(CommandType.PRINT, validator.validate("\n print \t"));
    }

    @Test
    public void singleRootPathShouldReturnPathCommandTypeTest() {
        assertEquals(CommandType.PATH, validator.validate("root"));
    }

    @Test
    public void pathWithLeadingSlashShouldReturnCommandTypeTest() {
        assertEquals(CommandType.PATH, validator.validate("/root"));
    }

    @Test
    public void rootFollowedBySlashShouldReturnUnknownCommandTypeTest() {
        assertEquals(CommandType.UNKNOWN, validator.validate("root/"));
    }

    @Test
    public void pathNotStartingWithLowercaseRootShouldReturnUnknownCommandTypeTest() {
        assertEquals(CommandType.UNKNOWN, validator.validate("Root/folder"));
        assertEquals(CommandType.UNKNOWN, validator.validate("rot/folder"));
        assertEquals(CommandType.UNKNOWN, validator.validate("Rot/folder/test"));
    }

    @Test
    public void pathWithDoubleSlashDelimiterShouldReturnUnknownCommandTypeTest() {
        assertEquals(CommandType.UNKNOWN, validator.validate("root//folder"));
        assertEquals(CommandType.UNKNOWN, validator.validate("root/folder//file.txt"));
    }

    @Test
    public void pathFollowedByExtraSlashShouldReturnUnknownCommandTypeTest() {
        assertEquals(CommandType.UNKNOWN, validator.validate("root/folder/"));
        assertEquals(CommandType.UNKNOWN, validator.validate("root/folder/file.txt/"));
    }

    @Test
    public void pathWithEmptyFileExtensionShouldReturnUnknownCommandTypeTest() {
        assertEquals(CommandType.UNKNOWN, validator.validate("root/file."));
    }

    @Test
    public void pathWithFoldersInFileShouldReturnUnknownCommandTypeTest() {
        assertEquals(CommandType.UNKNOWN, validator.validate("/root/file.txt/folder"));
        assertEquals(CommandType.UNKNOWN, validator.validate("/root/file1.txt/file2.txt/folder"));
    }
}