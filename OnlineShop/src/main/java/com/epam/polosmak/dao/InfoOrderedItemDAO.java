package com.epam.polosmak.dao;

import com.epam.polosmak.entity.InfoOrderedItem;

import java.sql.Connection;
import java.util.List;

public interface InfoOrderedItemDAO {
    boolean createInfoOrderedItems(Connection connection, List<InfoOrderedItem> orderedItems);
}
