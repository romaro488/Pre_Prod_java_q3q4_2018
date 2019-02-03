package com.epam.polosmak.service.dbService;

import com.epam.polosmak.dao.tranaction.TransactionManager;
import com.epam.polosmak.entity.Category;
import com.epam.polosmak.exception.DBException;
import com.epam.polosmak.repository.Repository;
import com.epam.polosmak.service.CategoryService;
import com.epam.polosmak.specification.Specification;
import com.epam.polosmak.specification.mysql.GetAllCategoriesMySqlSpecification;

import java.util.List;

public class DBCategoryService implements CategoryService {
    private TransactionManager transactionManager;
    private Repository<Category> repository;

    public DBCategoryService(TransactionManager transactionManager, Repository<Category> repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public List<Category> getAllCategories() throws DBException {
        Specification specification = new GetAllCategoriesMySqlSpecification();
        return transactionManager.execute(connection -> repository.query(connection, specification)
        );
    }
}