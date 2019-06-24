package com.dam.fs;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FileSystemTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void mkdirTest() {
        FileSystem fs = new FileSystem();
        assertTrue(fs.mkdir("/a"));

    }

    @Test
    public void mkdirNegativeTest() {
        FileSystem fs = new FileSystem();
        fs.mkdir("/a");
        assertFalse(fs.mkdir("/a"));
    }

    @Test
    public void listFileDirTest() {
        FileSystem fs = new FileSystem();
        fs.mkdir("/home");
        fs.mkdir("/home/user1");
        fs.mkdir("/home/user2");
        fs.createFile("/home", "readme.txt", "Hello World!");
        List<String> expected = Arrays.asList("readme.txt", "user1", "user2");

        assertEquals(expected, (fs.ls("/home")));
        System.out.println(fs.ls("/home"));
        System.out.println(fs.catFile("/home", "readme.txt"));

    }

    @Test
    public void createFileTest() {
        FileSystem fs = new FileSystem();
        fs.mkdir("/home");
        fs.createFile("/home", "readme.txt", "Hello World!");
        List<String> expected = Arrays.asList("readme.txt");

        assertEquals(expected, (fs.ls("/home")));
        System.out.println(fs.ls("/home"));

        assertEquals("Hello World!", (fs.catFile("/home", "readme.txt")));
        System.out.println(fs.catFile("/home", "readme.txt"));

    }
}