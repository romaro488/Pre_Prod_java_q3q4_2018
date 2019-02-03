package com.epam.polosmak.task5.part2.filter.extend.filter;

import java.io.File;

public abstract class Filter {

    /**
     * Object of another filter(chain).
     */
    private Filter nextFilter;

    /**
     * Create construct with object of next filter
     *
     * @param nextFilter Object of next filter
     */
    public Filter(Filter nextFilter) {
        this.nextFilter = nextFilter;
    }

    /**
     * Checks if file satisfy all the requirements
     *
     * @param file file need to filtered
     * @return true if file satisfy all the requirements and false if not
     */
    public boolean doFilter(File file) {
        return check(file);
    }

    /**
     * Checks if file satisfy the requirement of next filter
     *
     * @param file file need to filtered
     * @return true if file satisfy the requirements of next filter or next filter is null
     */
    public boolean checkNext(File file) {
        return nextFilter == null || nextFilter.check(file);
    }

    /**
     * Checks if file satisfy the requirement of current filter
     *
     * @param file file need to filtered
     * @return true if file satisfy the requirements of current filter
     */
    public abstract boolean check(File file);
}