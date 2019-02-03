package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.service.StoreService;

import java.util.Scanner;

public class GetItemCommand implements Command {
    private StoreService storeService;
    private Scanner scanner;

    public GetItemCommand(StoreService storeService, Scanner scanner) {
        this.storeService = storeService;
        this.scanner = scanner;
    }


    @Override
    public void execute() {
        System.out.println("Enter item name: ");
        String request = scanner.nextLine();
        if (storeService.isExist(request)) {
            System.out.print(storeService.getProduct(request).getName());
            System.out.print(" | " + storeService.getProduct(request).getPrice());
        } else {
            System.err.printf("the item %s is not exist", request);
        }
    }
}
