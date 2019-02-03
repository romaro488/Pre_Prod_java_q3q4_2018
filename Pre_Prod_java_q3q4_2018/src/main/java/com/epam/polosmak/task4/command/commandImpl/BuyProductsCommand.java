package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task1.entity.Order;
import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.constant.Constants;
import com.epam.polosmak.task4.service.CartService;
import com.epam.polosmak.task4.service.OrderService;
import com.epam.polosmak.task4.util.DateUtil;

import java.util.Map;
import java.util.Scanner;

public class BuyProductsCommand implements Command {

    private OrderService orderService;
    private CartService cartService;
    private Scanner scanner;

    public BuyProductsCommand(OrderService orderService, CartService cartService, Scanner scanner) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (!cartService.isEmpty()) {
            System.out.println("Enter date of order (dd.MM.yyyy)");
            String date = scanner.nextLine();
            orderService.buyProducts(DateUtil.convertStringToDate(date), new Order(cartService.getProducts()));
            double totalCost = 0;
            System.out.println(Constants.ORDER_LIST);
            for (Map.Entry<Sportswear, Integer> m : cartService.getProducts().entrySet()) {
                System.out.println(m.getKey() + " countOfProducts = " + m.getValue() + "}");
                totalCost += m.getKey().getPrice() * m.getValue();
            }
            System.out.println(Constants.TOTAL_PRICE + totalCost);

            cartService.getProducts().clear();
        } else {
            System.err.println("cart is empty\nadd some product to cart before buy product");
        }
    }
}
