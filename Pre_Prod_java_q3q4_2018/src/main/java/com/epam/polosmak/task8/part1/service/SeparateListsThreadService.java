package com.epam.polosmak.task8.part1.service;

import com.epam.polosmak.task8.part1.thread.PrimeNumbersSeparateList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class SeparateListsThreadService extends AbstractThreadService {

    public SeparateListsThreadService(int numberFrom, int numberTo, int numberOfThreads) {
        super(numberFrom, numberTo, numberOfThreads);
    }

    @Override
    public List<Integer> startExecutorThreads() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Integer> resultList = new ArrayList<>();
        List<Future<List<Integer>>> futureList = new ArrayList<>();

        int start = numberFrom;
        for (int i = 0; i < numberOfThreads; i++) {
            Future<List<Integer>> futureTask = executor.submit(new PrimeNumbersSeparateList(start++, numberOfThreads, numberTo));
            futureList.add(futureTask);
        }

        long startTime = System.currentTimeMillis();
        System.out.println("startTime = " + startTime + " ms");
        for (Future<List<Integer>> future : futureList) {
            resultList.addAll(future.get());
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(0, 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("endTime = " + endTime + " ms");
        System.out.println("executionTime = " + (endTime - startTime) + " ms");
        Collections.sort(resultList);
        System.out.println(resultList + "\n");
        return resultList;
    }

    @Override
    public List<Integer> startThreads() throws ExecutionException, InterruptedException {
        List<Integer> resultList = new ArrayList<>();
        List<Thread> threadsList = new ArrayList<>();
        List<FutureTask<List<Integer>>> tasks = new ArrayList<>();

        int start = numberFrom;
        for (int i = 0; i < numberOfThreads; i++) {
            FutureTask<List<Integer>> task = new FutureTask<>(new PrimeNumbersSeparateList(start++, numberOfThreads, numberTo));
            Thread thread = new Thread(task);
            tasks.add(task);
            threadsList.add(thread);
        }

        long startTime = System.currentTimeMillis();
        System.out.println("startTime =" + startTime + " ms");
        for (Thread thread : threadsList) {
            thread.start();
        }
        for (FutureTask<List<Integer>> listFutureTask : tasks) {
            resultList.addAll(listFutureTask.get());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("endTime = " + endTime + " ms");
        System.out.println("execution time = " + (endTime - startTime) + " ms");
        Collections.sort(resultList);
        System.out.println(resultList + "\n");
        return resultList;
    }
}
