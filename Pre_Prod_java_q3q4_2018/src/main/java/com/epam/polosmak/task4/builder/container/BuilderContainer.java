package com.epam.polosmak.task4.builder.container;

import com.epam.polosmak.task4.builder.Builder;
import com.epam.polosmak.task4.builder.impl.ShortBuilder;
import com.epam.polosmak.task4.builder.impl.SingletBuilder;
import com.epam.polosmak.task4.builder.impl.SportswearBuilder;
import com.epam.polosmak.task4.builder.impl.TshirtsBuilder;
import com.epam.polosmak.task4.constant.Constants;
import com.epam.polosmak.task4.inputHandler.Input;
import com.epam.polosmak.task4.inputHandler.impl.ManualInputHandler;
import com.epam.polosmak.task4.inputHandler.impl.RandomInputHandler;

import java.util.HashMap;
import java.util.Map;

public class BuilderContainer {

    private String inputType;

    private Map<Integer, Builder> commandMap = new HashMap<>();

    public BuilderContainer(String inputType) {
        this.inputType = inputType;
        commandMap.put(0, new SportswearBuilder(setInputMethod()));
        commandMap.put(1, new TshirtsBuilder(setInputMethod()));
        commandMap.put(2, new SingletBuilder(setInputMethod()));
        commandMap.put(3, new ShortBuilder(setInputMethod()));
    }

    public Builder getKey(int key) {
        return commandMap.get(key);
    }

    private Input setInputMethod() {
        Map<String, Input> inputMap = new HashMap<>();
        inputMap.put(Constants.MANUAL, new ManualInputHandler());
        inputMap.put(Constants.RANDOM, new RandomInputHandler());
        return inputMap.get(inputType);
    }
}
