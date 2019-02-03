package com.epam.polosmak.task4.repository;

import com.epam.polosmak.task1.entity.Sportswear;

import java.io.IOException;
import java.util.Map;

/**
 * Interface consist basic methods for ProductsRepository map.
 */
public interface ProductsRepository {

    Map<String, Sportswear> getProductMap();

    void addProduct(Sportswear sportswear);

    void initSerialization() throws IOException;

    void initDeserialization();

    Sportswear getProductByName(String productName);
}
