package com.epam.polosmak.dao;

import com.epam.polosmak.entity.Order;

import java.sql.Connection;

public interface OrderDAO {
    int create(Connection connection, Order order);
}
