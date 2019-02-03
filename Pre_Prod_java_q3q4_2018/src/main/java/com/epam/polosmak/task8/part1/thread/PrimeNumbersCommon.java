package com.epam.polosmak.task8.part1.thread;

import java.util.List;

import static com.epam.polosmak.task8.part1.util.IsPrimeNumber.isPrime;

public class PrimeNumbersCommon implements Runnable {
    private final List<Integer> list;
    private int start;
    private int step;
    private int stop;

    public PrimeNumbersCommon(List<Integer> list, int start, int step, int stop) {
        this.list = list;
        this.start = start;
        this.step = step;
        this.stop = stop;
    }

    @Override
    public void run() {
        for (int i = start; i < stop; i += step) {
            if (isPrime(i)) {
                synchronized (list) {
                    list.add(i);
                }
            }
        }
    }
}
