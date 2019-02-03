package com.epam.polosmak.task5.part2.filter.extend.filter;

import java.io.File;

public class FilterByExtension extends Filter {

    private String extension;

    public FilterByExtension(Filter nextFilter, String extension) {
        super(nextFilter);
        this.extension = extension;
    }

    @Override
    public boolean check(File file) {
        return file.toString().endsWith(extension.toLowerCase()) && checkNext(file);
    }
}