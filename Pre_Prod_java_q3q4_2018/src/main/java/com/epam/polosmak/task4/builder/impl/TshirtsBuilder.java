package com.epam.polosmak.task4.builder.impl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task1.entity.Tshirts;
import com.epam.polosmak.task4.inputHandler.Input;
import com.epam.polosmak.task4.reflection.resourceBundle.ReflectionResourceBundle;


public class TshirtsBuilder extends SportswearBuilder {

    private static final String SLEEVES = "availabilityOfSleeves(true|false ?)";
    private static final String SIZE = "Enter size";

    private Input input;

    public TshirtsBuilder(Input input) {
        super(input);
        this.input = input;
        sportswear = new Tshirts();
    }

    @Override
    public Sportswear build() {
        sportswear = super.build();
        reflectionResourceBundle = new ReflectionResourceBundle(Tshirts.class);
        ((Tshirts) sportswear).setSize(input.writeAndSetValue(SIZE));
        ((Tshirts) sportswear).setAvailabilityOfSleeves(input.writeAndValidateBooleanInput(SLEEVES));
        return sportswear;
    }
}
