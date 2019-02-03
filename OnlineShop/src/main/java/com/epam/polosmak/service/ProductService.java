package com.epam.polosmak.service;

import com.epam.polosmak.entity.Product;
import com.epam.polosmak.web.bean.ProductFilter;

import java.util.List;

public interface ProductService {
    List<Product> getFilteredProducts(ProductFilter filterBean);

    int getFilteredProductsCount(ProductFilter filterBean);

}
