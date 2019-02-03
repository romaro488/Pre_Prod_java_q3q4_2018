package com.epam.polosmak.task8;

import com.epam.polosmak.task8.part1.service.CommonListThreadService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonListThreadServiceTest {
    private static List<Integer> expectedList;
    private static int from;
    private static int to;
    private static int toOnBigRange;
    private static int threads;
    private static int singleThread;

    private CommonListThreadService service;

    @BeforeClass
    public static void initData() {
        expectedList = asList(2, 3, 5, 7, 11, 13, 17, 19);
        from = 0;
        to = 20;
        toOnBigRange = 120000;
        threads = 3;
        singleThread = 1;
    }

    @Test
    public void shouldReturnTrueIfServiceWithSeveralThreadsWorkFasterThanWithSingleOnBigRange_whenStartThreadsCalls() throws InterruptedException {
        service = new CommonListThreadService(from, toOnBigRange, Runtime.getRuntime().availableProcessors());
        long severalThreadsTime = System.currentTimeMillis();
        service.startThreads();
        service = new CommonListThreadService(from, toOnBigRange, singleThread);
        long singleThreadTime = System.currentTimeMillis();
        service.startThreads();

        assertTrue(severalThreadsTime < singleThreadTime);
    }

    @Test
    public void shouldReturnTrueIfServiceWithSeveralThreadsWorkFasterThanWithSingleOnBigRange_whenStartExecutorThreadsCalls() throws InterruptedException {
        service = new CommonListThreadService(from, toOnBigRange, Runtime.getRuntime().availableProcessors());
        long severalThreadsTime = System.currentTimeMillis();
        service.startThreads();
        service = new CommonListThreadService(from, toOnBigRange, singleThread);
        service.startExecutorThreads();
        long singleThreadTime = System.currentTimeMillis();

        assertTrue(severalThreadsTime < singleThreadTime);
    }

    @Test
    public void shouldReturnListWithPrimeNumbersInRange_whenStartThreadsCalls() throws InterruptedException {

        service = new CommonListThreadService(from, to, threads);
        List<Integer> actual = service.startThreads();

        assertTrue(isEqualCollection(expectedList, actual));
    }

    @Test
    public void shouldReturnListWithPrimeNumbersInRange_whenStartExecutorThreadsCalls() throws InterruptedException {

        service = new CommonListThreadService(from, to, threads);
        List<Integer> actual = service.startExecutorThreads();

        assertTrue(isEqualCollection(expectedList, actual));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_whenServiceInitAndTOInRangeLessThanFROM() {
        service = new CommonListThreadService(to, from, threads);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_whenServiceInitAndFROMInRangeLessThanZERO() {
        service = new CommonListThreadService(-1, to, threads);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_whenServiceInitAndNumberOfThreadsEqualsZERO() {
        service = new CommonListThreadService(from, to, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException_whenServiceInitAndNumberOfThreadsLessThanZERO() {
        service = new CommonListThreadService(from, to, -1);
    }
}
