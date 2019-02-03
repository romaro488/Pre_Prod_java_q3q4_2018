package com.epam.polosmak.task4.reflection.resourceBundle;


import java.util.ResourceBundle;

public class ReflectionResourceBundle {

    private static final String RESOURCE_BUNDLE_NAME = "locale";
    private ResourceBundle resourceBundle;
    private Class aClass;


    public ReflectionResourceBundle(Class aClass) {
        this.aClass = aClass;
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
    }
}
