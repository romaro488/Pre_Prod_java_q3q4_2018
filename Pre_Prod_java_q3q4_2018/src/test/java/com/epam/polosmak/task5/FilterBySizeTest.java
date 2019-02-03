package com.epam.polosmak.task5;

import com.epam.polosmak.task5.part2.filter.extend.filter.FilterBySize;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilterBySizeTest {

    private final String FILE_PATH = "part1ForTest.txt";
    private final double MIN_SIZE_IN_BYTES = 0;
    private final double MAX_SIZE_IN_BYTES = 1 * 1024;
    private final double SIZE_LESS_THAN_SIZE_OF_FILE_IN_BYTES = 0.5;
    private File fileForTest;
    private FilterBySize filterBySize;

    @Before
    public void setUp() throws IOException {
        fileForTest = new File(FILE_PATH);
        if (fileForTest.exists()) {
            fileForTest.delete();
        }
        fileForTest.createNewFile();
        fileForTest = new File(FILE_PATH.substring(0, FILE_PATH.lastIndexOf(".")));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileForTest));
        writer.write("line1");
        writer.close();
        filterBySize = new FilterBySize(null, MIN_SIZE_IN_BYTES, MAX_SIZE_IN_BYTES);
    }

    @After
    public void after() {
        fileForTest.delete();
    }

    @Test
    public void checkShouldReturnTrueIfSizeOfFileIsInPeriod() {
        assertTrue(filterBySize.check(fileForTest));
    }

    @Test
    public void checkShouldReturnFalseIfSizeOfFileIsNotInPeriod() {
        filterBySize = new FilterBySize(null, MIN_SIZE_IN_BYTES, SIZE_LESS_THAN_SIZE_OF_FILE_IN_BYTES);
        assertFalse(filterBySize.check(fileForTest));
    }

    @Test
    public void checkNextShouldReturnTrueIfNextFilterIsNull() {
        assertTrue(filterBySize.checkNext(fileForTest));
    }
}