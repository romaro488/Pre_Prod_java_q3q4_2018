package com.epam.polosmak.entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> cart;

    public Cart() {
        cart = new HashMap<>();
    }

    public void addToCart(Product item, int quantity) {
        if (cart.containsKey(item)) {
            cart.put(item, cart.get(item) + quantity);
        } else {
            cart.put(item, quantity);
        }
    }

    public void deleteFromCart(Product item) {
        cart.remove(item);
    }

    public void clearCart() {
        cart.clear();
    }

    public void subtract(Product item) {
        if (cart.containsKey(item)) {
            int quantity = cart.get(item);
            if (quantity == 1) {
                cart.remove(item);
            } else {
                cart.put(item, cart.get(item) - 1);
            }
        }
    }

    public Map<Product, Integer> getAllItems() {
        return cart;
    }

    public int getCountItems() {
        return cart.values()
                .stream()
                .mapToInt(value -> value)
                .sum();
    }

    public int getTotalPrice() {
        return cart.entrySet()
                .stream()
                .mapToInt(value -> value.getKey().getPrice().intValue() * value.getValue())
                .sum();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + cart +
                '}';
    }
}