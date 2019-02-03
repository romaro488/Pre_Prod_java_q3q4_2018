package com.epam.polosmak.task8.part1.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.epam.polosmak.task8.part1.util.IsPrimeNumber.isPrime;

public class PrimeNumbersSeparateList implements Callable<List<Integer>> {
    private int start;
    private int step;
    private int stop;

    public PrimeNumbersSeparateList(int start, int step, int stop) {
        this.start = start;
        this.step = step;
        this.stop = stop;
    }

    @Override
    public List<Integer> call() {
        List<Integer> tempList = new ArrayList<>();
        for (int i = start; i < stop; i += step) {
            if (isPrime(i)) {
                tempList.add(i);
            }
        }
        return tempList;
    }
}
