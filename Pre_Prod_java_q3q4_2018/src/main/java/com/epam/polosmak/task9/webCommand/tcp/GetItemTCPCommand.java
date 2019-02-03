package com.epam.polosmak.task9.webCommand.tcp;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.service.StoreService;
import com.epam.polosmak.task9.webCommand.WebCommand;

import java.util.Objects;

public class GetItemTCPCommand implements WebCommand {

    private StoreService storeService;
    private static final String WRONG_COMMAND = "Wrong command";

    public GetItemTCPCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public String getCommand(String request) {
        if (Objects.nonNull(request)) {
            Sportswear product = storeService.getProduct(request);
            if (Objects.nonNull(product)) {
                return String.format("%s | %s", product.getName(), product.getPrice());
            }
        }
        return WRONG_COMMAND + " " + request;
    }
}
