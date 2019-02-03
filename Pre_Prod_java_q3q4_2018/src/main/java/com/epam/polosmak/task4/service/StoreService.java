package com.epam.polosmak.task4.service;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task1.entity.Store;

import java.io.IOException;

public interface StoreService {

    void addProduct(Sportswear sportswear);

    Sportswear getProduct(String productName);

    boolean isExist(String productName);

    Store getProducts();

    void initSerialization() throws IOException;

    void initDeserialization();
}
