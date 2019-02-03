package com.epam.polosmak.task4.repository;

import com.epam.polosmak.task1.entity.Order;

import java.util.Date;
import java.util.TreeMap;

/**
 * Interface consist basic methods for OrderRepository map.
 */
public interface OrderRepository {

    void buyAllProducts(Date date, Order cart);

    Order getOrderByDate(Date date);

    TreeMap<Date, Order> getProducts();
}
