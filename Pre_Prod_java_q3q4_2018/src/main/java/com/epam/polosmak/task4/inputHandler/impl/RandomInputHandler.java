package com.epam.polosmak.task4.inputHandler.impl;

import com.epam.polosmak.task4.inputHandler.Input;

import java.util.Random;

public class RandomInputHandler implements Input {

    private static final String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int COUNT_OF_GENERATED_SYMBOLS = 5;
    private Random random = new Random();

    public RandomInputHandler() {
    }

    @Override
    public String writeAndSetValue(String value) {
        StringBuilder salt = new StringBuilder();
        while (salt.length() < COUNT_OF_GENERATED_SYMBOLS) {
            int index = (int) (random.nextFloat() * SALT_CHARS.length());
            salt.append(SALT_CHARS.charAt(index));
        }
        return salt.toString();
    }

    @Override
    public int writeAndSetPrice(String value) {
        return random.nextInt(1000);
    }

    @Override
    public Boolean writeAndValidateBooleanInput(String value) {
        return random.nextBoolean();
    }
}
