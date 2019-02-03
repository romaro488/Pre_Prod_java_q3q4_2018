package com.epam.polosmak.service.dbService;

import com.epam.polosmak.dao.tranaction.TransactionManager;
import com.epam.polosmak.entity.Product;
import com.epam.polosmak.repository.Repository;
import com.epam.polosmak.service.ProductService;
import com.epam.polosmak.specification.Specification;
import com.epam.polosmak.specification.mysql.GetFilteredProductMySqlSpecification;
import com.epam.polosmak.web.bean.ProductFilter;

import java.util.List;

public class DBProductService implements ProductService {
    private TransactionManager transactionManager;
    private Repository<Product> repository;

    public DBProductService(TransactionManager transactionManager, Repository<Product> repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public List<Product> getFilteredProducts(ProductFilter filterBean) {
        Specification specification = new GetFilteredProductMySqlSpecification(filterBean);
        return transactionManager.execute(connection -> repository.query(connection, specification)
        );
    }

    @Override
    public int getFilteredProductsCount(ProductFilter filterBean) {
        Specification specification = new GetFilteredProductMySqlSpecification(filterBean);
        return transactionManager.execute(connection -> repository.getCount(connection, specification)
        );
    }
}
