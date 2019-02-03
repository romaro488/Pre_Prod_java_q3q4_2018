package com.epam.polosmak.task4.inputHandler;

public interface Input {

    String writeAndSetValue(String value);

    int writeAndSetPrice(String value);

    Boolean writeAndValidateBooleanInput(String value);

}
