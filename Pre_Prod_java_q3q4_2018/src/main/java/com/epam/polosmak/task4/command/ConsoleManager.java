package com.epam.polosmak.task4.command;

import com.epam.polosmak.task4.context.ApplicationContext;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {

    private ApplicationContext applicationContext = new ApplicationContext();

    /**
     * Method load console interface.
     */
    public void command() throws IOException {

        Scanner scanner = new Scanner(System.in);

        CommandController commandController = new CommandController(scanner, applicationContext);

        while (true) {
            System.out.println("Please enter the command\n" +
                    ("---------------------------"));
            System.out.println("CONSOLE SHOP \n" +
                    "1)store\n" +
                    "2)add to cart\n" +
                    "3)cart\n" +
                    "4)total cost\n" +
                    "5)buy products\n" +
                    "6)get order by date\n" +
                    "7)get order by nearest date\n" +
                    "8)get last products\n" +
                    "9)add to store\n" +
                    "10)add to store R\n" +
                    "11)help\n" +
                    "0)exit");
            String command = scanner.nextLine().trim();
            commandController.handleCommand(command);
            System.out.println();
        }
    }
}
