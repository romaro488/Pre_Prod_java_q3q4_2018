package com.epam.polosmak.task5;

import com.epam.polosmak.task5.part2.filter.extend.filter.FilterByName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilterByNameTest {

    private final String FILE_PATH = "part1ForTest.txt";
    private File fileForTest;
    private FilterByName filterByName;

    @Before
    public void setUp() throws IOException {
        fileForTest = new File(FILE_PATH);
        if (fileForTest.exists()) {
            fileForTest.delete();
        }
        fileForTest.createNewFile();
        String nameOfFile = FILE_PATH.substring(0, FILE_PATH.lastIndexOf("."));
        fileForTest = new File(FILE_PATH);
        filterByName = new FilterByName(null, nameOfFile);
    }

    @After
    public void after() {
        fileForTest.delete();
    }

    @Test
    public void checkShouldReturnTrueIfFileNameIsTrue() {
        assertTrue(filterByName.check(fileForTest));
    }

    @Test
    public void checkShouldReturnFalseIfFileNameIsNotTrue() {
        filterByName = new FilterByName(null, "FileNameNotExist");
        assertFalse(filterByName.check(fileForTest));
    }

    @Test
    public void checkNextShoulReturnTrueIfNextFilterIsNull() {
        assertTrue(filterByName.checkNext(fileForTest));
    }
}