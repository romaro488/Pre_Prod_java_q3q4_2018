package com.epam.polosmak.service;

import com.epam.polosmak.entity.Product;

import java.util.List;

public interface ItemService {
    List<Product> getAllItems();

    Product getItemById(int id);
}
