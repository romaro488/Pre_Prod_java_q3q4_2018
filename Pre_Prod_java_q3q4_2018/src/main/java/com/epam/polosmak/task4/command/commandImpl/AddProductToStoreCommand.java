package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.inputHandler.InputStrategy;
import com.epam.polosmak.task4.service.StoreService;

public class AddProductToStoreCommand implements Command {

    private InputStrategy inputStrategy;
    private StoreService storeService;

    public AddProductToStoreCommand(InputStrategy inputStrategy, StoreService storeService) {
        this.inputStrategy = inputStrategy;
        this.storeService = storeService;
    }

    @Override
    public void execute() {
        System.out.println("Choice sportswear to adding");
        System.out.println("0) Sportswear");
        System.out.println("1) Tshirt");
        System.out.println("2) Singlet");
        System.out.println("3) Shorts ");

        Sportswear sportswear = inputStrategy.buildProduct().build();
        storeService.addProduct(sportswear);
        System.out.println(storeService.getProduct(sportswear.getName()));
    }
}
