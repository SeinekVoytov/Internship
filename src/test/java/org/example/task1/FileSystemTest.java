package org.example.task1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
class FileSystemTest {

    private static final InputStream DEFAULT_INPUT = System.in;
    private static final PrintStream DEFAULT_OUTPUT = System.out;

    private static final String QUIT_COMMAND = "quit";
    private static final String PRINT_COMMAND = "print";

    private ByteArrayOutputStream testOut;
    private FileSystem fileSystem;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    private void initFileSystemForTest() {
        fileSystem = new FileSystem();
        fileSystem.start();
    }

    @Test
    public void quitCommandTest() {
        provideInput("quit");
        initFileSystemForTest();
        assertTrue(fileSystem.isQuit());
    }

    @Test
    public void printCommandWithDefaultRootDirectoryTest() {
        provideInput(String.format("%s\n%s",PRINT_COMMAND, QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals("root/\n", testOut.toString());
    }

    @Test
    public void testPathCommandWithOneFileTest() {
        provideInput(String.format("%s\n%s\n%s", "root/file.txt", PRINT_COMMAND, QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals("root/\n\tfile.txt\n", testOut.toString());
    }

    @Test
    public void testPathCommandWithOneFolderTest() {
        provideInput(String.format("%s\n%s\n%s", "/root/folder", PRINT_COMMAND, QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals("root/\n\tfolder/\n", testOut.toString());
    }

    @Test
    public void twoNestedDirectoriesTest() {
        provideInput(String.format("%s\n%s\n%s", "root/folder1/folder2", PRINT_COMMAND, QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals("root/\n\tfolder1/\n\t\tfolder2/\n", testOut.toString());
    }

    @Test
    public void directoryWithFileAndFolderTest() {
        provideInput(String.format("%s\n%s\n%s\n%s", "/root/folder", "root/file.txt", PRINT_COMMAND, QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals("root/\n\tfolder/\n\tfile.txt\n", testOut.toString());
    }

    @Test
    public void invalidPathCommandTest() {
        provideInput(String.format("%s\n%s", "root/file.", QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals(FileSystem.UNKNOWN_COMMAND_MSG + "\n", testOut.toString());
    }

    @Test
    public void invalidPrintCommandTest() {
        provideInput(String.format("%s\n%s", "Print", QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals(FileSystem.UNKNOWN_COMMAND_MSG + "\n", testOut.toString());
    }

    @Test
    public void invalidQuitCommandTest() {
        provideInput(String.format("%s\n%s", "Quit", QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals(FileSystem.UNKNOWN_COMMAND_MSG + "\n", testOut.toString());
    }

    @Test
    public void addingAlreadyExistingFileTest() {
        provideInput(String.format("%s\n%s\n%s", "root/file.xml", "/root/file.xml", QUIT_COMMAND));
        initFileSystemForTest();
        assertEquals("File already exists\n", testOut.toString());
    }

    @AfterAll
    public static void placeDefaultStreamsBack() {
        System.setIn(DEFAULT_INPUT);
        System.setOut(DEFAULT_OUTPUT);
    }
}