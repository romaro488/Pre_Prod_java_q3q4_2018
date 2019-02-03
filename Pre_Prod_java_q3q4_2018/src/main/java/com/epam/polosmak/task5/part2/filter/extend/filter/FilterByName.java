package com.epam.polosmak.task5.part2.filter.extend.filter;

import java.io.File;

public class FilterByName extends Filter {

    private static final String FILE_SPLITTER = ".";
    private String fileName;

    public FilterByName(Filter nextFilter, String fileName) {
        super(nextFilter);
        this.fileName = fileName;
    }

    @Override
    public boolean check(File file) {
        return compareByName(file) && checkNext(file);
    }

    private boolean compareByName(File file) {
        String nameOfFile = file.getName().substring(0, file.getName().lastIndexOf(FILE_SPLITTER));
        return fileName.equalsIgnoreCase(nameOfFile);
    }
}