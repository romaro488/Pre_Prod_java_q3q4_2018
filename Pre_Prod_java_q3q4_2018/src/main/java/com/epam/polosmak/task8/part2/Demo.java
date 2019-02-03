package com.epam.polosmak.task8.part2;

import com.epam.polosmak.task4.inputHandler.Input;

public class Demo {
    public static void main(String[] args) throws Exception {
        Initializer initializer = new Initializer();
        Input interaction = initializer.init();
        SequenceFinder finder = new SequenceFinder(interaction);
        finder.startFinder();
    }
}
