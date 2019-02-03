package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.constant.Constants;
import com.epam.polosmak.task4.repository.impl.LastAddedProductsToCart;

import java.util.Map;

public class LastAddedProductsToCartCommand implements Command {

    private LastAddedProductsToCart lastAddedProductsToCart;

    public LastAddedProductsToCartCommand(LastAddedProductsToCart lastAddedProductsToCart) {
        this.lastAddedProductsToCart = lastAddedProductsToCart;
    }

    @Override
    public void execute() {
        if (!lastAddedProductsToCart.isEmpty()) {
            System.out.println(Constants.LAST_PRODUCT_IN_CART);
            for (Map.Entry entry : lastAddedProductsToCart.getLastAddedProductsInCart().entrySet()) {
                System.out.println(entry.getValue() + "}");
            }
        } else {
            System.out.println("list of last products in cart is empty");
        }
    }
}
