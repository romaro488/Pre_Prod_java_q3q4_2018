package com.epam.polosmak.task5.part2.filter.extend.filter.container;

import com.epam.polosmak.task5.part2.filter.extend.filter.DefaultFilter;
import com.epam.polosmak.task5.part2.filter.extend.filter.Filter;
import com.epam.polosmak.task5.part2.filter.extend.filterContructor.FilterByDateConstructor;
import com.epam.polosmak.task5.part2.filter.extend.filterContructor.FilterByExtensionConstructor;
import com.epam.polosmak.task5.part2.filter.extend.filterContructor.FilterByNameConstructor;
import com.epam.polosmak.task5.part2.filter.extend.filterContructor.FilterBySizeConstructor;

public class FilterContainer {

    private Filter filter;

    private Filter DateFilterConstruct(Filter sizeFilter) {
        return new FilterByDateConstructor().construct(sizeFilter);
    }

    private Filter SizeFilterConstruct(Filter extFilter) {
        return new FilterBySizeConstructor().construct(extFilter);
    }

    private Filter ExtensionFilterConstruct(Filter nameFilter) {
        return new FilterByExtensionConstructor().construct(nameFilter);
    }

    private Filter NameFilterConstruct(Filter defaultFilter) {
        return new FilterByNameConstructor().construct(defaultFilter);
    }

    public Filter getFilter() {
        Filter defaultFilter = new DefaultFilter(null);
        filter = NameFilterConstruct(filter);
        filter = ExtensionFilterConstruct(filter);
        filter = SizeFilterConstruct(filter);
        filter = DateFilterConstruct(filter);
        return defaultFilter == null ? filter : filter;
    }
}