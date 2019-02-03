package com.epam.polosmak.task8.part2;

import com.epam.polosmak.task4.inputHandler.Input;
import com.epam.polosmak.task4.inputHandler.impl.ManualInputHandler;

class Initializer {
    Input init() {
        return new ManualInputHandler();
    }
}
