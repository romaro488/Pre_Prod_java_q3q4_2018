package com.epam.polosmak.task4.repository.impl;

import com.epam.polosmak.task1.entity.Order;
import com.epam.polosmak.task4.repository.OrderRepository;

import java.util.Date;
import java.util.TreeMap;

public class OrderRepositoryImpl implements OrderRepository {

    private TreeMap<Date, Order> orderMap = new TreeMap<>();

    @Override
    public void buyAllProducts(Date date, Order cart) {
        getProducts().put(date, cart);
    }

    @Override
    public Order getOrderByDate(Date date) {
        return getProducts().get(date);
    }

    @Override
    public TreeMap<Date, Order> getProducts() {
        return orderMap;
    }
}
