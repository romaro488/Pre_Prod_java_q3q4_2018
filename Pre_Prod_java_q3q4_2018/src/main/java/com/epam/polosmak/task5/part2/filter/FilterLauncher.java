package com.epam.polosmak.task5.part2.filter;

import com.epam.polosmak.task5.part2.filter.extend.filter.Filter;
import com.epam.polosmak.task5.part2.filter.extend.filter.container.FilterContainer;
import com.epam.polosmak.task5.part2.util.InputHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterLauncher {

    private Filter filter;
    private List<String> fileFounds;

    /**
     * Create constructor of class
     */
    public FilterLauncher() {
        fileFounds = new ArrayList<>();
    }

    /**
     * Gets files satisfied the requirements of all filters
     *
     * @return list files satisfied the requirements. And null if client did not choose any kind
     * of filter or if directory does not contain any files
     */
    public List<String> search() {
        String dirPath = new InputHandler().getDirectoryPath();
        File[] files = new File(dirPath).listFiles();
        if (files == null) {
            return null;
        }
        filter = new FilterContainer().getFilter();
        if (filter == null) {
            return null;
        }
        searchInDirectory(files);
        return fileFounds;
    }

    /**
     * Gets files satisfied the requirements of all filters
     *
     * @param files list of file to be searched
     */
    private void searchInDirectory(File[] files) {
        for (File file : files) {
            if (file.isFile()) {
                if (filter.doFilter(file)) {
                    fileFounds.add(file.toString());
                }
            } else {
                searchInDirectory(Objects.requireNonNull(file.listFiles()));
            }
        }
    }
}