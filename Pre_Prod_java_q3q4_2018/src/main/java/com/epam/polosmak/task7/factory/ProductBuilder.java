package com.epam.polosmak.task7.factory;

import com.epam.polosmak.task7.exception.UnmodifiableException;
import com.epam.polosmak.task7.product.Product;
import com.epam.polosmak.task7.product.Sportswear;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProductBuilder implements Builder, InvocationHandler {

    private Product product;

    public ProductBuilder(Product product) {
        this.product = product;
    }

    @Override
    public Product creator() {
        return product = (Product) setProxyClass();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            throw new UnmodifiableException();
        }
        return method.invoke(product, args);
    }

    private Object setProxyClass() {
        return Proxy.newProxyInstance(
                Sportswear.class.getClassLoader(),
                new Class[]{Product.class},
                new ProductBuilder(product));
    }
}
