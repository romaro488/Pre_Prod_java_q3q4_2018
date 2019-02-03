package com.epam.polosmak.dao.impl;

import com.epam.polosmak.dao.InfoOrderedItemDAO;
import com.epam.polosmak.entity.InfoOrderedItem;
import com.epam.polosmak.exception.DBException;
import com.epam.polosmak.message.Message;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class InfoOrderedItemDAOImpl implements InfoOrderedItemDAO {
    private static final Logger LOGGER = Logger.getLogger(InfoOrderedItemDAOImpl.class);
    private static final String CREATE_ORDERED_ITEM = "INSERT INTO `onlineshop`.`ordered_product`" +
            " VALUES(DEFAULT,?,?,?,?)";

    @Override
    public boolean createInfoOrderedItems(Connection connection, List<InfoOrderedItem> orderedItems) {
        LOGGER.debug(Message.USER_START_CREATING);
        ResultSet resultSet;
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDERED_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            for (InfoOrderedItem orderedItem : orderedItems) {
                statement.setInt(1, orderedItem.getOrderId());
                statement.setInt(2, orderedItem.getItemId());
                statement.setInt(3, orderedItem.getCountOfItems());
                statement.setInt(4, orderedItem.getPrice().intValue());
                statement.executeUpdate();
            }

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = true;
            }
            resultSet.close();
            LOGGER.debug(Message.ORDERED_ITEM_CREATE_SUCCESS);
            return result;
        } catch (SQLException e) {
            LOGGER.error(Message.ERR_CANNOT_CREATE_ORDERED_ITEM, e);
            throw new DBException(Message.ERR_CANNOT_CREATE_ORDERED_ITEM, e);
        }
    }
}
