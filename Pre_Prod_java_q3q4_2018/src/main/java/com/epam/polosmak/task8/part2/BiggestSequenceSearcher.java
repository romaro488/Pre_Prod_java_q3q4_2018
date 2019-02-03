package com.epam.polosmak.task8.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class BiggestSequenceSearcher implements Runnable {

    private byte[] bytes;
    private int firstIndex = 0;
    private int lastIndex = 0;
    private volatile int maxLength = 1;
    private volatile boolean isAlive;

    @Override
    public synchronized void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("thread was Interrupted");
            }
            calculateSequence();
            isAlive = false;
        }
    }

    protected synchronized void search() {
        isAlive = true;
        notify();
    }

    synchronized void initBytes(Path path) throws IOException {
        this.bytes = Files.readAllBytes(path);
        this.firstIndex = 0;
        this.lastIndex = 0;
        this.maxLength = 0;
    }

    byte[] getResult() {
        return Arrays.copyOfRange(bytes, lastIndex - maxLength + 1, lastIndex + 1);
    }

    int getMaxLength() {
        return maxLength;
    }

    boolean isAlive() {
        return isAlive;
    }

    int getFirstIndex() {
        return firstIndex - maxLength + 1;
    }

    int getLastIndex() {
        return lastIndex - maxLength + 1;
    }

    private void calculateSequence() {
        int[][] result = new int[bytes.length][bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < bytes.length; j++) {
                if (bytes[i] == bytes[j]) {
                    if (i != j && i != 0 && j != 0) {
                        result[i][j] = result[i - 1][j - 1] + 1;
                    } else {
                        result[i][j] = 1;
                    }
                    if (result[i][j] > maxLength) {
                        maxLength = result[i][j];
                        firstIndex = i;
                        lastIndex = j;
                    }
                }
            }
        }
    }
}
