package com.epam.polosmak.task4.builder.impl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.builder.Builder;
import com.epam.polosmak.task4.inputHandler.Input;
import com.epam.polosmak.task4.reflection.resourceBundle.ReflectionResourceBundle;

public class SportswearBuilder implements Builder {
    private static final String ENTER_NAME = "Enter name";
    private static final String ENTER_PRICE = "Enter price";
    private static final String ENTER_COLOR = "Enter color";
    protected Sportswear sportswear;
    ReflectionResourceBundle reflectionResourceBundle;
    private Input input;

    public SportswearBuilder(Input input) {
        this.input = input;
        sportswear = new Sportswear();
    }

    @Override
    public Sportswear build() {
        reflectionResourceBundle = new ReflectionResourceBundle(Sportswear.class);
        sportswear.setName(input.writeAndSetValue(ENTER_NAME));
        sportswear.setPrice(input.writeAndSetPrice(ENTER_PRICE));
        sportswear.setColor(input.writeAndSetValue(ENTER_COLOR));
        return sportswear;
    }
}
