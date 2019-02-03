package com.epam.polosmak.task4.service;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.repository.impl.LastAddedProductsToCart;

import java.util.Map;

public interface CartService {

    void addProductToCart(Sportswear product, int countOfProduct);

    LastAddedProductsToCart getLastAddedProductsToCart();

    Map<Sportswear, Integer> getProducts();

    boolean isEmpty();

    double getPriceOfProductsInCart();
}
