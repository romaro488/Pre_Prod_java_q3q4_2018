package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.reflection.reflectionHandler.ReflectionInputHandler;
import com.epam.polosmak.task4.service.StoreService;

public class AddProductToStoreReflectionCommand implements Command {

    private ReflectionInputHandler reflectionInputHandler;
    private StoreService storeService;

    public AddProductToStoreReflectionCommand(ReflectionInputHandler reflectionInputHandler, StoreService storeService) {
        this.reflectionInputHandler = reflectionInputHandler;
        this.storeService = storeService;
    }

    @Override
    public void execute() {
        System.out.println("Choice productEntity to adding");
        System.out.println("0) Sportswear");
        System.out.println("1) Tshirts");
        System.out.println("2) Singlet");
        System.out.println("3) Shorts");


        Sportswear sportswear = reflectionInputHandler.productBuilder().load(reflectionInputHandler.getEntityName());
        storeService.addProduct(sportswear);
        System.out.println(storeService.getProduct(sportswear.getName()));
    }
}
