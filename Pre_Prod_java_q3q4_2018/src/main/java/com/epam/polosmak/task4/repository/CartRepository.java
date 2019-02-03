package com.epam.polosmak.task4.repository;

import com.epam.polosmak.task1.entity.Sportswear;

import java.util.Map;

/**
 * Interface consist basic methods for CartRepository map.
 */
public interface CartRepository {

    Map<Sportswear, Integer> getCartMap();

    void addToCart(Sportswear product, int countOfProduct);

    void clearCart();
}
