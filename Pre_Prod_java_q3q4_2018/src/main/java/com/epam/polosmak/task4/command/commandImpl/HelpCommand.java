package com.epam.polosmak.task4.command.commandImpl;

import com.epam.polosmak.task4.command.Command;

public class HelpCommand implements Command {

    @Override
    public void execute() {
        help();
    }

    private void help() {
        System.out.println();
    }

}
