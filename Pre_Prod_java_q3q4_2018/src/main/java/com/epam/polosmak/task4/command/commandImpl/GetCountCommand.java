package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.service.StoreService;

public class GetCountCommand implements Command {

    private StoreService storeService;

    public GetCountCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void execute() {
        System.out.println("Count of products in store: " + storeService.getProducts().getStore().size());
    }
}
