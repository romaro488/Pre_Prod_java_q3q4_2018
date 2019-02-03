package com.epam.polosmak.dao.impl;

import com.epam.polosmak.dao.OrderDAO;
import com.epam.polosmak.entity.Order;
import com.epam.polosmak.exception.DBException;
import com.epam.polosmak.message.Message;
import org.apache.log4j.Logger;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAOImpl.class);
    private static final String CREATE_ORDER = "INSERT INTO `onlineshop`.`orders` VALUES(DEFAULT,?,?,?)";


    @Override
    public int create(Connection connection, Order entity) {
        LOGGER.debug(Message.USER_START_CREATING);
        ResultSet resultSet;
        int resultId = 0;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(entity.getLocalDate()));
            statement.setInt(2, entity.getUserId());
            statement.setInt(3, entity.getOrderStatus().getStatusId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                resultId = resultSet.getInt(1);
            }
            resultSet.close();
            LOGGER.debug(Message.ORDER_CREATE_SUCCESS);
            return resultId;
        } catch (SQLException e) {
            throw new DBException(Message.ERR_CANNOT_CREATE_ORDER, e);
        }
    }
}
