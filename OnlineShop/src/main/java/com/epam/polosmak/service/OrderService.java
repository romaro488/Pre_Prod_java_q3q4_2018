package com.epam.polosmak.service;

import com.epam.polosmak.entity.Cart;
import com.epam.polosmak.entity.User;

public interface OrderService {
    boolean createOrder(Cart cart, User user);
}
