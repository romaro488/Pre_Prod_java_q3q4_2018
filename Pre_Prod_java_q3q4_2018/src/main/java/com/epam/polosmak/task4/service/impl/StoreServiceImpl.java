package com.epam.polosmak.task4.service.impl;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task1.entity.Store;
import com.epam.polosmak.task4.repository.ProductsRepository;
import com.epam.polosmak.task4.service.StoreService;

import java.io.IOException;

/**
 * Class contain methods to display some info in Store.
 */
public class StoreServiceImpl implements StoreService {

    private ProductsRepository productsRepository;

    public StoreServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void addProduct(Sportswear sportswear) {
        productsRepository.addProduct(sportswear);
    }

    @Override
    public Sportswear getProduct(String productName) {
        return productsRepository.getProductByName(productName);
    }

    @Override
    public boolean isExist(String productName) {
        return productsRepository.getProductMap().containsKey(productName);
    }

    @Override
    public void initSerialization() throws IOException {
        productsRepository.initSerialization();
    }

    @Override
    public void initDeserialization() {
        productsRepository.initDeserialization();
    }

    public Store getProducts() {
        return new Store(productsRepository.getProductMap());
    }
}
