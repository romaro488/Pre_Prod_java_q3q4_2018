package com.epam.polosmak.dao.impl;

import com.epam.polosmak.dao.UserDAO;
import com.epam.polosmak.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUserDAO implements UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(MySQLUserDAO.class);

    private static final String CHECK_IF_USER_EXIST = "SELECT 1 from user where email = ?;";
    private static final String GET_USER_BY_EMAIL = "SELECT * from user WHERE email = ?";
    private static final String INSERT_NEW_USER = "INSERT INTO onlineshop.user (first_name, last_name, email, password, is_sign_up,user_avatar_extension) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public User getUserByEmail(Connection connection, String email) throws SQLException {
        User result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = extractUser(rs);
            }
        }
        LOG.info("Get user by email from DB : result --> {}", result);
        return result;
    }

    @Override
    public void create(Connection connection, User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER)) {
            int k = 1;
            preparedStatement.setString(k++, user.getFirstName());
            preparedStatement.setString(k++, user.getLastName());
            preparedStatement.setString(k++, user.getEmail());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setBoolean(k++, user.isSignUp());
            preparedStatement.setString(k++, user.getUserAvatarExtension());
            preparedStatement.executeUpdate();
            LOG.info("Saved user in DB : user --> {}", user);
        }
    }

    @Override
    public boolean isUserExist(Connection connection, String email) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IF_USER_EXIST)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                LOG.info("User with email exist : email --> {}", email);
                return true;
            } else {
                LOG.info("User with email not exist : email --> {}", email);
                return false;
            }
        }
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setUserAvatarExtension(resultSet.getString("user_avatar_extension"));
        LOG.info("Extracted user : user --> {}", user);
        return user;
    }
}
