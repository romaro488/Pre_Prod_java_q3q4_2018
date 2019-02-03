package com.epam.polosmak.task9.webCommand.tcp;

import com.epam.polosmak.task4.service.StoreService;
import com.epam.polosmak.task9.webCommand.WebCommand;

public class GetCountTCPCommand implements WebCommand {

    private StoreService storeService;

    public GetCountTCPCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public String getCommand(String command) {
        return String.valueOf("Count of products in store: " + storeService.getProducts().getStore().size());
    }
}
