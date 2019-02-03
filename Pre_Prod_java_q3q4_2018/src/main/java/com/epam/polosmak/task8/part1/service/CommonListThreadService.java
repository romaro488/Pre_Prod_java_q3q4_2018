package com.epam.polosmak.task8.part1.service;

import com.epam.polosmak.task8.part1.thread.PrimeNumbersCommon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommonListThreadService extends AbstractThreadService {

    public CommonListThreadService(int numberFrom, int numberTo, int numberOfThreads) {
        super(numberFrom, numberTo, numberOfThreads);
    }

    @Override
    public List<Integer> startExecutorThreads() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        final List<Integer> resultList = new ArrayList<>();
        final List<Thread> threadsList = new ArrayList<>();

        int start = numberFrom;
        for (int i = 0; i < numberOfThreads; i++) {
            threadsList.add(new Thread(new PrimeNumbersCommon(resultList, start++, numberOfThreads, numberTo)));
        }

        long startTime = System.currentTimeMillis();
        System.out.println("startTime = " + startTime + " ms");
        for (Thread thread1 : threadsList) {
            executor.submit(thread1);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("endTime = " + endTime + " ms");
        System.out.println("executionTime = " + (endTime - startTime) + " ms");
        Collections.sort(resultList);
        System.out.println(resultList + "\n");
        return resultList;
    }

    @Override
    public List<Integer> startThreads() throws InterruptedException {
        List<Integer> resultList = new ArrayList<>();
        List<Thread> threadsList = new ArrayList<>();

        int start = numberFrom;
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(new PrimeNumbersCommon(resultList, start++, numberOfThreads, numberTo));
            threadsList.add(thread);
        }

        long startTime = System.currentTimeMillis();
        System.out.println("startTime = " + startTime + " ms");
        for (Thread thread : threadsList) {
            thread.start();
        }

        for (Thread thread : threadsList) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("endTime = " + endTime);
        System.out.println("executionTime = " + (endTime - startTime) + " ms");
        Collections.sort(resultList);
        System.out.println(resultList + "\n");
        return resultList;
    }
}
