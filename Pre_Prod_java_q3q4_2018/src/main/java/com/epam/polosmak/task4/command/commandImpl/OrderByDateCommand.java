package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.service.OrderService;
import com.epam.polosmak.task4.util.DateUtil;

import java.util.Map;
import java.util.Scanner;

public class OrderByDateCommand implements Command {

    private OrderService orderService;
    private Scanner scanner;

    public OrderByDateCommand(OrderService orderService, Scanner scanner) {
        this.orderService = orderService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter date DD.MM.YY $");
        String date = scanner.nextLine();
        System.out.println("OrderRepository: " + date);
        if (orderService.getOrdersByDate(DateUtil.convertStringToDate(date)) != null) {
            for (Map.Entry<Sportswear, Integer> entry : orderService.getOrdersByDate(DateUtil.convertStringToDate(date)).getOrder().entrySet()) {
                System.out.println(entry.getKey() + " countOfProducts = " + entry.getValue() + "}");
            }
        } else {
            System.err.println("date not found");
        }
    }
}
