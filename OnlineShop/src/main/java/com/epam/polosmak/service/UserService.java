package com.epam.polosmak.service;

import com.epam.polosmak.entity.User;
import com.epam.polosmak.exception.DBException;

public interface UserService {
    void createUser(User user) throws DBException;

    User getUser(String email) throws DBException;

    boolean isUserExist(String email) throws DBException;
}
