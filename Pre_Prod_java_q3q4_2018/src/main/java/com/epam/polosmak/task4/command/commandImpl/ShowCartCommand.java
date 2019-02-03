package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.constant.Constants;
import com.epam.polosmak.task4.service.CartService;

import java.util.Map;

import static com.epam.polosmak.task4.constant.Constants.ADD_TO_CART;

public class ShowCartCommand implements Command {

    private CartService cartService;

    public ShowCartCommand(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        if (!cartService.isEmpty()) {
            System.out.println(Constants.CART);
            for (Map.Entry entry : cartService.getProducts().entrySet()) {
                System.out.println(entry.getKey() + " countOfProduct = " + entry.getValue() + "}");
                System.out.println(cartService.getPriceOfProductsInCart());
            }
        } else {
            System.out.printf("cart is empty\nadd some product in cart with command - %s", ADD_TO_CART);
        }
    }
}
