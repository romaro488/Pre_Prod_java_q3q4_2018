package com.epam.polosmak.task4.builder.impl;

import com.epam.polosmak.task1.entity.Singlet;
import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.inputHandler.Input;
import com.epam.polosmak.task4.reflection.resourceBundle.ReflectionResourceBundle;

public class SingletBuilder extends TshirtsBuilder {

    private static final String THE_PRESENCE_OF_POCKETS = "thePresenceOfPockets true|false ?";
    private Input input;

    public SingletBuilder(Input input) {
        super(input);
        this.input = input;
        sportswear = new Singlet();
    }

    @Override
    public Sportswear build() {
        sportswear = super.build();
        reflectionResourceBundle = new ReflectionResourceBundle(Singlet.class);
        ((Singlet) sportswear).setManufacturer(input.writeAndSetValue("Enter manufacturer"));
        ((Singlet) sportswear).setThePresenceOfPockets(input.writeAndValidateBooleanInput(THE_PRESENCE_OF_POCKETS));
        return sportswear;
    }
}
