package com.epam.polosmak.service;

import com.epam.polosmak.entity.Category;
import com.epam.polosmak.exception.DBException;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories() throws DBException;
}
