package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.constant.Constants;
import com.epam.polosmak.task4.service.CartService;

public class TotalPriceCommand implements Command {

    private CartService cartService;

    public TotalPriceCommand(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        if (!cartService.isEmpty()) {
            System.out.println(Constants.TOTAL_PRICE);
            System.out.println(cartService.getPriceOfProductsInCart());
        } else {
            System.out.println("cart is empty");
        }
    }
}
