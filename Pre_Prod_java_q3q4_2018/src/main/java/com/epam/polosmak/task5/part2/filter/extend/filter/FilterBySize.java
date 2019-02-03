package com.epam.polosmak.task5.part2.filter.extend.filter;

import java.io.File;

public class FilterBySize extends Filter {

    private double minSize;
    private double maxSize;

    public FilterBySize(Filter nextFilter, double minSize, double maxSize) {
        super(nextFilter);
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public boolean check(File file) {
        return isInSizePeriod(file) && checkNext(file);
    }

    private boolean isInSizePeriod(File file) {
        double sizeOfFile = file.length();
        return sizeOfFile >= minSize && sizeOfFile <= maxSize;
    }
}