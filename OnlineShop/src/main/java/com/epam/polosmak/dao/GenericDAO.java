package com.epam.polosmak.dao;

import com.epam.polosmak.entity.Entity;

public interface GenericDAO<T extends Entity> {

    T get(int id);

    int create(T entity);

}
