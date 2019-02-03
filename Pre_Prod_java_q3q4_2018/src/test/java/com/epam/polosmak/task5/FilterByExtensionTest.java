package com.epam.polosmak.task5;

import com.epam.polosmak.task5.part2.filter.extend.filter.FilterByExtension;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilterByExtensionTest {

    private final String FILE_PATH = "part1ForTest.txt";
    private String EXTENSION = "txt";
    private File fileForTest;
    private FilterByExtension filterByExtension;

    @Before
    public void setUp() throws IOException {
        fileForTest = new File(FILE_PATH);
        if (fileForTest.exists()) {
            fileForTest.delete();
        }
        fileForTest.createNewFile();
        filterByExtension = new FilterByExtension(null, EXTENSION);
    }

    @After
    public void after() {
        fileForTest.delete();
    }

    @Test
    public void checkShouldReturnTrueIfFileExtensionIsTrue() {
        assertTrue(filterByExtension.check(fileForTest));
    }

    @Test
    public void checkShouldReturnFalseIfFileExtensionIsNotTrue() {
        filterByExtension = new FilterByExtension(null, "notExistExtension");
        assertFalse(filterByExtension.check(fileForTest));
    }

    @Test
    public void checkNextShoulReturnTrueIfNextFilterIsNull() {
        assertTrue(filterByExtension.checkNext(fileForTest));
    }
}