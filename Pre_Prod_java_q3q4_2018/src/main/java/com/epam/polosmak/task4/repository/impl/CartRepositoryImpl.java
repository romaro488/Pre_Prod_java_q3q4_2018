package com.epam.polosmak.task4.repository.impl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.repository.CartRepository;

import java.util.HashMap;
import java.util.Map;

public class CartRepositoryImpl implements CartRepository {

    private Map<Sportswear, Integer> cartMap = new HashMap<>();

    @Override
    public Map<Sportswear, Integer> getCartMap() {
        return cartMap;
    }

    @Override
    public void addToCart(Sportswear product, int countOfProduct) {
        cartMap.put(product, countOfProduct);
    }

    @Override
    public void clearCart() {
        cartMap.clear();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
