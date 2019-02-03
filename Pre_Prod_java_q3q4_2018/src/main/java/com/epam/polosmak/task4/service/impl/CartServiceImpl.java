package com.epam.polosmak.task4.service.impl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.repository.CartRepository;
import com.epam.polosmak.task4.repository.impl.LastAddedProductsToCart;
import com.epam.polosmak.task4.service.CartService;

import java.util.Map;

/**
 * Class contain methods to display some info in CartRepository.
 */
public class CartServiceImpl implements CartService {

    private LastAddedProductsToCart lastAddedProductsToCart;
    private CartRepository cartRepository;

    public CartServiceImpl(LastAddedProductsToCart lastAddedProductsToCart, CartRepository cartRepository) {
        this.lastAddedProductsToCart = lastAddedProductsToCart;
        this.cartRepository = cartRepository;
    }

    @Override
    public void addProductToCart(Sportswear product, int countOfProduct) {
        cartRepository.addToCart(product, countOfProduct);
        for (Sportswear p : cartRepository.getCartMap().keySet()) {
            getLastAddedProductsToCart().addToLastProductsInCart(p);
        }
    }

    @Override
    public LastAddedProductsToCart getLastAddedProductsToCart() {
        return lastAddedProductsToCart;
    }

    @Override
    public Map<Sportswear, Integer> getProducts() {
        return cartRepository.getCartMap();
    }

    public boolean isEmpty() {
        return cartRepository.getCartMap().isEmpty();
    }

    @Override
    public double getPriceOfProductsInCart() {
        double totalPrice = 0;
        for (Map.Entry<Sportswear, Integer> entry : cartRepository.getCartMap().entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }
}
