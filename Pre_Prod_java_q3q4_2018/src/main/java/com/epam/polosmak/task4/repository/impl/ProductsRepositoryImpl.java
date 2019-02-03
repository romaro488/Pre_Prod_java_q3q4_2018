package com.epam.polosmak.task4.repository.impl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.repository.ProductsRepository;
import com.epam.polosmak.task4.util.SerializationUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProductsRepositoryImpl implements ProductsRepository {

    private static final String FILE_SERIALIZATION = "src/store.txt";

    private Map<String, Sportswear> productMap = new TreeMap<>();

    @Override
    public Map<String, Sportswear> getProductMap() {
        return productMap;
    }

    @Override
    public void initSerialization() {
        SerializationUtil.serialize(getProductMap(), FILE_SERIALIZATION);
    }

    @Override
    public void initDeserialization() {
        Map store = (Map) SerializationUtil.deserialize(FILE_SERIALIZATION);
        if (store.isEmpty()) {
            System.out.println("Store is empty, please add some sportswear to the store.");
        } else {
            productMap = new HashMap<>(store);
        }
    }

    @Override
    public void addProduct(Sportswear sportswear) {
        productMap.put(sportswear.getName(), sportswear);
    }

    @Override
    public Sportswear getProductByName(String productName) {
        return productMap.get(productName);
    }
}
