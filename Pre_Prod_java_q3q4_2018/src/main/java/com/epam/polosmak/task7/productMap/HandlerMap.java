package com.epam.polosmak.task7.productMap;

import com.epam.polosmak.task7.factory.Builder;
import com.epam.polosmak.task7.product.Product;

import java.lang.reflect.Proxy;

public class HandlerMap implements Builder {

    @Override
    public Product creator() {
        return (Product) Proxy.newProxyInstance(
                Product.class.getClassLoader(),
                new Class[]{Product.class},
                new ProductMap());
    }
}
