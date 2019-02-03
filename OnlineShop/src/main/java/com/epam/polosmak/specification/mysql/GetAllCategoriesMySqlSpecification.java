package com.epam.polosmak.specification.mysql;

import com.epam.polosmak.specification.MySqlSpecification;

import java.util.List;

public class GetAllCategoriesMySqlSpecification implements MySqlSpecification {
    @Override
    public String toMySqlQuery() {
        return "SELECT * FROM onlineshop.category";
    }

    @Override
    public String countMySqlQuery() {
        return "SELECT  COUNT(*) AS count FROM onlineshop.category";
    }

    @Override
    public List<Object> getParams() {
        return null;
    }
}
