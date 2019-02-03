package com.epam.polosmak.task1.entity;

import java.io.Serializable;
import java.util.Map;

public class Store implements Serializable {

    private Map<String, Sportswear> store;

    public Store(Map<String, Sportswear> store) {
        this.store = store;
    }

    public Map<String, Sportswear> getStore() {
        return store;
    }

    @Override
    public String toString() {
        return "Store = " + store +
                '}';
    }
}
