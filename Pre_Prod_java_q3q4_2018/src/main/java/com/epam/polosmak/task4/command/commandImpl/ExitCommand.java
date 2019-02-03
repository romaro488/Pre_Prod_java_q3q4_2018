package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task4.command.Command;
import com.epam.polosmak.task4.service.StoreService;

import java.io.IOException;

public class ExitCommand implements Command {

    private StoreService storeService;

    public ExitCommand(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void execute() throws IOException {
        storeService.initSerialization();
        System.out.println("Good bye! Have a nice day!");
        System.exit(0);
    }
}
