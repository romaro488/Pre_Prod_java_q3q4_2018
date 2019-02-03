package com.epam.polosmak.task4.builder.impl;

import com.epam.polosmak.task1.entity.Shorts;
import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.inputHandler.Input;
import com.epam.polosmak.task4.reflection.resourceBundle.ReflectionResourceBundle;

public class ShortBuilder extends SportswearBuilder {

    private static String LANDING_TYPE = "Enter landing type";
    private Input input;

    public ShortBuilder(Input input) {
        super(input);
        this.input = input;
        sportswear = new Shorts();
    }

    @Override
    public Sportswear build() {
        sportswear = super.build();
        reflectionResourceBundle = new ReflectionResourceBundle(Shorts.class);
        ((Shorts) sportswear).setLanding(input.writeAndSetValue(LANDING_TYPE));
        return sportswear;
    }
}
