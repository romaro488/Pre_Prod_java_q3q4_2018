package com.epam.polosmak.task5.part2.filter.extend.filter;

import java.io.File;

public class DefaultFilter extends Filter {

    public DefaultFilter(Filter nextFilter) {
        super(nextFilter);
    }

    @Override
    public boolean check(File file) {
        return true;
    }
}