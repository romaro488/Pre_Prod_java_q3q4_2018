package com.epam.polosmak.task5.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileWrapper implements Iterable<String> {

    private String filename;

    public FileWrapper(String filename) {
        this.filename = filename;
    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl<String>();
    }

    private class IteratorImpl<String> implements Iterator<String> {

        private Scanner scanner;

        public IteratorImpl() {
            try {
                this.scanner = new Scanner(new File(filename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean hasNext() {
            try {
                if (scanner.hasNextLine()) {
                    return true;
                }
            } catch (IllegalStateException exception) {
                return false;
            }
            scanner.close();
            return false;

        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (String) scanner.nextLine();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
