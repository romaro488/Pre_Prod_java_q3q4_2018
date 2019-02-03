package com.epam.polosmak.task4.inputHandler.impl;

import com.epam.polosmak.task4.inputHandler.Input;

import java.util.Objects;
import java.util.Scanner;

import static com.epam.polosmak.task4.constant.Constants.BOOLEAN_REGEXP;
import static com.epam.polosmak.task4.constant.Constants.NUMBER_REGEXP;

public class ManualInputHandler implements Input {

    private Scanner sc;

    public ManualInputHandler() {
        sc = new Scanner(System.in);
    }

    @Override
    public int writeAndSetPrice(String value) {
        String line = null;
        System.out.println(value);
        while (sc.hasNext()) {
            line = sc.nextLine();
            if (line.matches(NUMBER_REGEXP)) {
                return Integer.parseInt(line);
            } else {
                System.out.println("' " + line + " ' Not valid, try again.");
            }
        }
        return Integer.parseInt(Objects.requireNonNull(line));
    }

    @Override
    public Boolean writeAndValidateBooleanInput(String value) {
        String line;
        System.out.println(value);
        while (sc.hasNext()) {
            line = sc.nextLine();
            if (line.matches(BOOLEAN_REGEXP)) {
                return Boolean.parseBoolean(line);
            } else {
                System.out.println("' " + line + " 'Not valid to(true | false), try again. ");
            }
        }
        return true;
    }

    @Override
    public String writeAndSetValue(String value) {
        System.out.println(value);
        return sc.nextLine();
    }
}
