package com.epam.polosmak.task4;

import com.epam.polosmak.task4.command.ConsoleManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleManager consoleCommand = new ConsoleManager();
        consoleCommand.command();

    }
}
