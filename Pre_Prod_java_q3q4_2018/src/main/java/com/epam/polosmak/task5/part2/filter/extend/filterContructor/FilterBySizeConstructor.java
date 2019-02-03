package com.epam.polosmak.task5.part2.filter.extend.filterContructor;

import com.epam.polosmak.task5.part2.constants.constants;
import com.epam.polosmak.task5.part2.filter.SourceConstructor;
import com.epam.polosmak.task5.part2.filter.extend.filter.Filter;
import com.epam.polosmak.task5.part2.filter.extend.filter.FilterBySize;

public class FilterBySizeConstructor extends SourceConstructor {

    @Override
    public Filter construct(Filter filter) {
        printMessage.print(constants.FIND_FILE_BY_SIZE);
        if (inputHandler.getSelect() != AGREE) {
            return filter;
        }
        printMessage.println(constants.ENTER_SIZE_RANGE);
        return new FilterBySize(filter, inputHandler.getSize() * 1024, inputHandler.getSize() * 1024);
    }
}