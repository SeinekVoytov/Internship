package org.example.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandValidatorTest {

    private final CommandValidator validator = new CommandValidator();

    @Test
    void validate_ShouldReturnUnknownCommandType_WhenEmptyCommandSpecified() {
        assertEquals(CommandType.UNKNOWN, validator.validate(""));
    }

    @Test
    void validate_ShouldReturnUnknownCommandType_WhenBlankCommandSpecified() {
        assertEquals(CommandType.UNKNOWN, validator.validate("\t"));
    }

    @Test
    void validate_ShouldReturnValidCommandType_WhenCommandWithTrailingSpacesSpecified() {
        assertAll(
                () -> assertEquals(CommandType.QUIT, validator.validate("  quit    ")),
                () -> assertEquals(CommandType.PATH, validator.validate("\troot\t")),
                () -> assertEquals(CommandType.PRINT, validator.validate("\n print \t"))
        );
    }

    @Test
    void validate_ShouldReturnPathCommandType_WhenSingleRootPathSpecified() {
        assertEquals(CommandType.PATH, validator.validate("root"));
    }

    @Test
    void validate_ShouldReturnPathCommandType_WhenPathWithSingleSlashSpecified() {
        assertEquals(CommandType.PATH, validator.validate("/root"));
    }

    @Test
    void validate_ShouldReturnUnknownCommandType_WhenPathIsRootFollowedBySlash() {
        assertEquals(CommandType.UNKNOWN, validator.validate("root/"));
    }

    @Test
    void validate_ShouldReturnUnknownCommandType_WhenPathStartsNotFromLowercaseRoot() {
        assertAll(
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("Root/folder")),
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("rot/folder")),
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("Rot/folder/test"))
        );
    }

    @Test
    void validate_ShouldReturnUnknownCommandType_WhenPathContainDoubleSlashDelimiter() {
        assertAll(
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("root//folder")),
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("root/folder//file.txt"))
        );
    }

    @Test
    void validate_ShouldReturnUnknownCommandType_WhenPathIsFollowedByExtraSlash() {
        assertAll(
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("root/folder/")),
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("root/folder/file.txt/"))
        );
    }

    @Test
    void validate_ShouldReturnUnknownCommandType_WhenPathContainFileWithoutExtension() {
        assertEquals(CommandType.UNKNOWN, validator.validate("root/file."));
    }

    @Test
    void validate_ShouldReturnUnknownCommandType_WhenPathContainFoldersInFile() {
        assertAll(
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("/root/file.txt/folder")),
                () -> assertEquals(CommandType.UNKNOWN, validator.validate("/root/file1.txt/file2.txt/folder"))
        );
    }
}