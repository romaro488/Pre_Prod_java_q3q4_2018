package com.epam.polosmak.task7.productMap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ProductMap implements InvocationHandler {

    private Map<String, Object> map = new HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            return map.put(method.getName().substring(3).toLowerCase(), args[0]);
        } else if (method.getName().startsWith("get")) {
            return map.get(method.getName().substring(3).toLowerCase());
        }
        throw new NoSuchMethodException();
    }
}
