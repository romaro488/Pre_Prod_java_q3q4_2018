package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.constant.Constants;
import com.epam.polosmak.task4.service.StoreService;

import java.util.Map;

public class ProductListCommand implements Command {

    private StoreService storeService;

    public ProductListCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void execute() {
        System.out.println(Constants.PRODUCT_SHOP);
        for (Map.Entry<String, Sportswear> entry : storeService.getProducts().getStore().entrySet()) {
            System.out.println(entry.getValue() + "}");
        }
    }
}

