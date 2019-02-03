package com.epam.polosmak.task5.part2.filter.extend.filterContructor;

import com.epam.polosmak.task5.part2.constants.constants;
import com.epam.polosmak.task5.part2.filter.SourceConstructor;
import com.epam.polosmak.task5.part2.filter.extend.filter.Filter;
import com.epam.polosmak.task5.part2.filter.extend.filter.FilterByName;

public class FilterByNameConstructor extends SourceConstructor {

    @Override
    public Filter construct(Filter filter) {
        printMessage.print(constants.FIND_FILE_BY_NAME);
        if (inputHandler.getSelect() != AGREE) {
            return filter;
        }
        printMessage.print(constants.ENTER_FILE_NAME);
        return new FilterByName(filter, inputHandler.getInput());
    }
}