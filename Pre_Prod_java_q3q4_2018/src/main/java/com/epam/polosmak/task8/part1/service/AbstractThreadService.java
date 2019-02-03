package com.epam.polosmak.task8.part1.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class AbstractThreadService {

    protected int numberFrom;
    protected int numberTo;
    protected int numberOfThreads;

    protected AbstractThreadService(int numberFrom, int numberTo, int numberOfThreads) {
        this.numberFrom = numberFrom;
        this.numberTo = numberTo;
        this.numberOfThreads = numberOfThreads;

        if (numberFrom < 0 || numberTo < numberFrom) {
            throw new IllegalArgumentException("Range cannot contains numbers less then ZERO or To cannot be less than From");
        }
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Number OF threads cannot be less or equals ZERO");
        }
    }

    public abstract List<Integer> startExecutorThreads() throws ExecutionException, InterruptedException;

    public abstract List<Integer> startThreads() throws InterruptedException, ExecutionException;
}
