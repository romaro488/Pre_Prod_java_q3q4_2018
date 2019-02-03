package com.epam.polosmak.dao;

import com.epam.polosmak.entity.Product;

import java.sql.Connection;
import java.util.List;

public interface ItemDAO extends GenericDAO<Product> {
    List<Product> getAllItems();

    Product getItemById(Connection connection, int id);
}
