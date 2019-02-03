package com.epam.polosmak.task9.webCommand.http;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.service.StoreService;
import com.epam.polosmak.task9.webCommand.WebCommand;

import java.util.Objects;

import static com.epam.polosmak.task9.factory.thread.ServerThread.header;

public class GetItemHttpCommand implements WebCommand {
    private static final String WRONG_COMMAND = "Wrong command";
    private StoreService storeService;

    public GetItemHttpCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public String getCommand(String request) {
        if (Objects.nonNull(request)) {
            Sportswear product = storeService.getProduct(request);
            if (Objects.nonNull(product)) {
                String json = String.format("{name: %s, price: %s}", product.getName(), product.getPrice());
                return header(json);
            }
        }
        return header(WRONG_COMMAND + " ") + request;
    }
}