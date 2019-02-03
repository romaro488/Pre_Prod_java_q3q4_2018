package com.epam.polosmak.task4.reflection.reflectionHandler;

import com.epam.polosmak.task4.reflection.reflectionBuilder.container.ReflectionBuilderContainer;
import com.epam.polosmak.task4.reflection.reflectionBuilder.impl.ReflectionBuilderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReflectionInputHandler {
    private Scanner scanner;
    private ReflectionBuilderContainer reflectionBuilderContainer;

    public ReflectionInputHandler() {
        scanner = new Scanner(System.in);
    }

    public void setTemplate(String inputType) {
        reflectionBuilderContainer = new ReflectionBuilderContainer(inputType);
    }

    public String getEntityName() {
        Map<Integer, String> commandMap = new HashMap<>();
        commandMap.put(0, "Sportswear");
        commandMap.put(1, "Tshirts");
        commandMap.put(2, "Singlet");
        commandMap.put(3, "Shorts");

        return commandMap.get(Integer.parseInt(scanner.nextLine()));
    }

    public ReflectionBuilderImpl productBuilder() {
        return reflectionBuilderContainer.getReflectionBuilder();
    }
}