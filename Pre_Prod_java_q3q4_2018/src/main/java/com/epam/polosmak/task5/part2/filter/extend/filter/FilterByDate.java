package com.epam.polosmak.task5.part2.filter.extend.filter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FilterByDate extends Filter {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
    private LocalDate start;
    private LocalDate end;

    public FilterByDate(Filter nextFilter, LocalDate start, LocalDate end) {
        super(nextFilter);
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean check(File file) {
        return compareByTimePeriod(file) && checkNext(file);
    }

    private boolean compareByTimePeriod(File file) {
        LocalDate fileDate = getLastModifiedDateOfFile(file);
        return fileDate.isAfter(start) && fileDate.isBefore(end);
    }

    private LocalDate getLastModifiedDateOfFile(File file) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        return LocalDate.parse(dateFormat.format(file.lastModified()));
    }
}