package com.epam.polosmak.task4.service;

import com.epam.polosmak.task1.entity.Order;
import com.epam.polosmak.task1.entity.Sportswear;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public interface OrderService {

    void buyProducts(Date date, Order cart);

    Order getOrdersByDate(Date date);

    Map<Sportswear, Integer> getOrderByNearestDate(Date date);

    TreeMap<Date, Order> getMap();
}
