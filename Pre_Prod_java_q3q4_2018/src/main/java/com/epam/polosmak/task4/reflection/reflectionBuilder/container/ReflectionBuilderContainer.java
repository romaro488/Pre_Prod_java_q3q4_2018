package com.epam.polosmak.task4.reflection.reflectionBuilder.container;

import com.epam.polosmak.task4.constant.Constants;
import com.epam.polosmak.task4.inputHandler.Input;
import com.epam.polosmak.task4.inputHandler.impl.ManualInputHandler;
import com.epam.polosmak.task4.inputHandler.impl.RandomInputHandler;
import com.epam.polosmak.task4.reflection.reflectionBuilder.impl.ReflectionBuilderImpl;

import java.util.HashMap;
import java.util.Map;

public class ReflectionBuilderContainer {
    private String type;

    public ReflectionBuilderContainer(String type) {
        this.type = type;
    }

    public ReflectionBuilderImpl getReflectionBuilder() {
        return new ReflectionBuilderImpl(setInputMethod());
    }

    private Input setInputMethod() {

        Map<String, Input> inputMap = new HashMap<>();
        inputMap.put(Constants.MANUAL, new ManualInputHandler());
        inputMap.put(Constants.RANDOM, new RandomInputHandler());
        return inputMap.get(type);
    }
}
