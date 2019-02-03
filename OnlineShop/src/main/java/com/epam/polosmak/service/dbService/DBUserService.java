package com.epam.polosmak.service.dbService;

import com.epam.polosmak.dao.UserDAO;
import com.epam.polosmak.dao.tranaction.TransactionManager;
import com.epam.polosmak.entity.User;
import com.epam.polosmak.exception.DBException;
import com.epam.polosmak.service.UserService;

public class DBUserService implements UserService {
    private TransactionManager transactionManager;
    private UserDAO userDAO;

    public DBUserService(TransactionManager transactionManager, UserDAO userDAO) {
        this.transactionManager = transactionManager;
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(User user) throws DBException {
        transactionManager.execute(connection -> {
                    userDAO.create(connection, user);
                    return null;
                }
        );
    }

    @Override
    public User getUser(String email) throws DBException {
        return transactionManager.execute(connection -> userDAO.getUserByEmail(connection, email)
        );
    }

    @Override
    public boolean isUserExist(String email) throws DBException {
        return transactionManager.execute(connection -> userDAO.isUserExist(connection, email)
        );
    }
}
