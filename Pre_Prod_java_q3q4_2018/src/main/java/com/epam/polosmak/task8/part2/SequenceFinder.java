package com.epam.polosmak.task8.part2;

import com.epam.polosmak.task4.inputHandler.Input;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SequenceFinder {
    private static final String ENTER_PATH = "\nEnter path (or exit):";
    private static final String FIRST_INPUT = "First Input: ";
    private static final String SECOND_INPUT = "Second Input: ";
    private static final String SEQUENCE = "\nSequence: ";
    private static final String MAX_LENGTH = "Max length: ";

    private Input interaction;
    private BiggestSequenceSearcher searcher;
    private Thread thread;

    protected SequenceFinder(Input interaction) {
        this.interaction = interaction;
        this.searcher = new BiggestSequenceSearcher();
        this.thread = new Thread(searcher);
    }

    protected void startFinder() throws Exception {
        thread.start();
        String resource;
        while (!(resource = interaction.writeAndSetValue(ENTER_PATH)).equals("exit")) {
            Path path;
            try {
                path = Paths.get(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(resource)).toURI());
            } catch (URISyntaxException | NullPointerException e) {
                System.out.println("Wrong path, or file does not exist");
                continue;
            }
            searcher.initBytes(path);
            searcher.search();
            monitorProgress();
            showResults();
        }
        stopFinder();
    }

    private void monitorProgress() throws InterruptedException {
        while (searcher.isAlive()) {
            Thread.sleep(1);
        }
    }

    private void showResults() {
        String result = new String(searcher.getResult());
        System.out.println(SEQUENCE + result);
        System.out.println(MAX_LENGTH + searcher.getMaxLength());
        System.out.println(FIRST_INPUT + searcher.getFirstIndex());
        System.out.println(SECOND_INPUT + searcher.getLastIndex());
    }

    private void stopFinder() {
        System.out.println("Searcher stopped.");
        System.exit(0);
    }
}
