package com.epam.polosmak.task4.service.impl;

import com.epam.polosmak.task1.entity.Order;
import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.repository.OrderRepository;
import com.epam.polosmak.task4.service.OrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class contain methods to display some info in OrderRepository.
 */
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void buyProducts(Date date, Order cart) {
        Map<Sportswear, Integer> copyCart = new HashMap<>(cart.getOrder());
        orderRepository.buyAllProducts(date, new Order(copyCart));
    }

    @Override
    public Order getOrdersByDate(Date date) {
        return orderRepository.getOrderByDate(date);
    }

    @Override
    public Map<Sportswear, Integer> getOrderByNearestDate(Date date) {
        Date low = orderRepository.getProducts().floorKey(date);
        Date high = orderRepository.getProducts().ceilingKey(date);
        if (low == null && high != null) {
            return orderRepository.getProducts().get(high).getOrder();
        }
        if (low != null && high == null) {
            return orderRepository.getProducts().get(low).getOrder();
        }
        if (low != null && high != null) {
            long currentTime = date.getTime();
            long lDiff = Math.abs(currentTime - low.getTime());
            long hDiff = Math.abs(currentTime - high.getTime());
            if (lDiff < hDiff) {
                return orderRepository.getProducts().get(low).getOrder();
            } else {
                return orderRepository.getProducts().get(high).getOrder();
            }
        }
        return null;
    }

    @Override
    public TreeMap<Date, Order> getMap() {
        return orderRepository.getProducts();
    }
}
