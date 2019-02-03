package com.epam.polosmak.task9.webCommand.http;

import com.epam.polosmak.task4.service.StoreService;
import com.epam.polosmak.task9.webCommand.WebCommand;

public class GetCountHttpCommand implements WebCommand {

    private StoreService storeService;

    public GetCountHttpCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public String getCommand(String command) {
        return "{count: " + storeService.getProducts().getStore().size() + "}";
    }
}
