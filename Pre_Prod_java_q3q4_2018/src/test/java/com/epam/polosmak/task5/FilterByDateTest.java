package com.epam.polosmak.task5;

import com.epam.polosmak.task5.part2.filter.extend.filter.FilterByDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilterByDateTest {

    private final String FILE_PATH = "part1ForTest.txt";

    private LocalDate START_OF_PERIOD = LocalDate.of(2018, 8, 18);
    private LocalDate END_OF_PERIOD = LocalDate.of(2018, 8, 30);
    private FilterByDate filterByDate;
    private File fileForTest;

    @Before
    public void setUp() throws IOException {
        fileForTest = new File(FILE_PATH);
        if (fileForTest.exists()) {
            fileForTest.delete();
        }
        fileForTest.createNewFile();
        filterByDate = new FilterByDate(null, START_OF_PERIOD, END_OF_PERIOD);
    }

    @After
    public void after() {
        fileForTest.delete();
    }

    @Test
    public void checkShouldReturnTrueIfLastModifiedTimeOfFileInPeriodOfTime() {
        assertTrue(filterByDate.check(fileForTest));
    }

    @Test
    public void checkShouldReturnFalseIfLastModifiedTimeOfFileNotInPeriodOfTime() {
        END_OF_PERIOD = LocalDate.of(2018, 8, 19);
        filterByDate = new FilterByDate(null, START_OF_PERIOD, END_OF_PERIOD);
        assertFalse(filterByDate.check(fileForTest));
    }

    @Test
    public void checkNextShouldReturnTrueIfNextFilterIsNull() {
        assertTrue(filterByDate.checkNext(fileForTest));
    }

}