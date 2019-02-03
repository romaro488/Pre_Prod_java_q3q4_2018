package com.epam.polosmak.task5.part2.messages.impl;

import com.epam.polosmak.task5.part2.messages.PrintMessage;

public class PrintMessageImpl implements PrintMessage {

    public static PrintMessageImpl getInstance() {
        return new PrintMessageImpl();
    }

    @Override
    public void print(String print) {
        System.out.println(print);
    }

    @Override
    public void println(String print) {
        System.out.println(print);
    }
}
