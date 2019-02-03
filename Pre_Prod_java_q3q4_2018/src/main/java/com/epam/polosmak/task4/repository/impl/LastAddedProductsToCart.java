package com.epam.polosmak.task4.repository.impl;

import com.epam.polosmak.task1.entity.Sportswear;

import java.util.LinkedHashMap;
import java.util.Map;

public class LastAddedProductsToCart {

    private static final int LAST_PRODUCTS_SIZE = 5;

    private Map<Sportswear, Sportswear> lastAddedProductsInCart = new LinkedHashMap() {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return this.size() > LAST_PRODUCTS_SIZE;
        }
    };

    public boolean isEmpty() {
        return lastAddedProductsInCart.isEmpty();
    }

    public void addToLastProductsInCart(Sportswear product) {
        lastAddedProductsInCart.put(product, product);
    }

    public Map<Sportswear, Sportswear> getLastAddedProductsInCart() {
        return lastAddedProductsInCart;
    }
}
