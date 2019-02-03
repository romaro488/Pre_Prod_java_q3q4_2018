package com.epam.polosmak.task4.reflection.reflectionBuilder.impl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.annotation.Product;
import com.epam.polosmak.task4.inputHandler.Input;
import com.epam.polosmak.task4.reflection.reflectionBuilder.ReflectionBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReflectionBuilderImpl implements ReflectionBuilder {

    private static final String PATH = "com.epam.polosmak.task1.entity.";
    private static final String RESOURCE_BUNDLE_NAME = "locale";
    private ResourceBundle resourceBundle;
    private Input input;
    private Sportswear sportswear;

    public ReflectionBuilderImpl(Input input) {
        this.input = input;
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
    }

    @Override
    public Sportswear load(String loadClass) {
        try {
            sportswear = (Sportswear) createObject(loadClass);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Class not found");
        }
        setParameter(Objects.requireNonNull(sportswear));
        return sportswear;
    }

    private void setParameter(Sportswear sportswear) {
        try {
            List<Method> allSetMethods = getAllSetters(sportswear.getClass().getMethods());
            for (Method m : allSetMethods) {
                Object[] obj = getParameters(m);
                m.invoke(sportswear, obj);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("Can't access to method");
        }
    }

    private Object[] getParameters(Method m) {
        Object[] args = new Object[1];
        Type[] type = m.getParameterTypes();

        if (String.class.equals(type[0])) {
            args[0] = input.writeAndSetValue(resourceBundle.getString(m.getName().substring(3).toLowerCase()));
        } else if (double.class.equals(type[0])) {
            args[0] = input.writeAndSetPrice(resourceBundle.getString(m.getName().substring(3).toLowerCase()));
        } else if (boolean.class.equals(type[0])) {
            args[0] = input.writeAndValidateBooleanInput(resourceBundle.getString(m.getName().substring(3)));
        }
        return args;
    }

    private List<Method> getAllSetters(Method[] methods) {
        Method m;
        List<Method> methodList = new ArrayList<>();
        for (Method method : methods) {
            m = method;
            if (m.getName().contains("set") && m.isAnnotationPresent(Product.class)) {
                methodList.add(method);
            }
        }
        return methodList;
    }

    private Object createObject(String loadClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return Class.forName(PATH + loadClass).newInstance();
    }
}
