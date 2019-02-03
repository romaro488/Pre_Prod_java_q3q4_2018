package com.epam.polosmak.task4.inputHandler;

import com.epam.polosmak.task4.builder.Builder;
import com.epam.polosmak.task4.builder.container.BuilderContainer;
import com.epam.polosmak.task4.constant.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.epam.polosmak.task4.constant.Constants.MANUAL;
import static com.epam.polosmak.task4.constant.Constants.RANDOM;

public class InputStrategy {

    private String inputType;
    private Scanner scanner;
    private BuilderContainer container;

    public InputStrategy() {
        scanner = new Scanner(System.in);
    }

    public void setTemplate() {
        System.out.println("choice input type\n" +
                " 0 - " + MANUAL +
                "\n 1 - " + RANDOM + " (default)");
        String type = scanner.nextLine();

        Map<String, String> map = new HashMap<>();
        map.put("0", MANUAL);
        map.put("1", RANDOM);

        inputType = map.getOrDefault(type, Constants.RANDOM);

        container = new BuilderContainer(inputType);
    }

    public String getInputType() {
        return inputType;
    }

    public Builder buildProduct() {
        return container.getKey(Integer.parseInt(scanner.nextLine()));
    }
}
