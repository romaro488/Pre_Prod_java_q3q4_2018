package com.epam.polosmak.dao.impl;

import com.epam.polosmak.dao.ItemDAO;
import com.epam.polosmak.dao.tranaction.ThreadLockHandler;
import com.epam.polosmak.entity.Product;
import com.epam.polosmak.exception.DBException;
import com.epam.polosmak.message.Message;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private static final Logger LOGGER = Logger.getLogger(ItemDAOImpl.class);
    private static final String GET_ALL_ITEMS = "SELECT * FROM `onlineshop`.`product`";
    private static final String GET_ITEM_BY_ID = "SELECT * FROM `onlineshop`.product WHERE product_id=(?)";

    private static final String ID = "product_id";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_PRICE = "product_price";
    private static final String PRODUCT_IMAGE = "product_image";
    private static final String PRODUCT_QUANTITY = "product_quantity";

    @Override
    public List<Product> getAllItems() {
        Connection connection = ThreadLockHandler.getConnection();
        ResultSet resultSet;
        List<Product> items;
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ITEMS)) {
            resultSet = statement.executeQuery();
            items = parseItemList(resultSet);
            LOGGER.debug(Message.ITEM_OBTAINED);
            resultSet.close();
            return items;
        } catch (SQLException e) {
            throw new DBException(Message.ERR_CANNOT_OBTAIN_ITEM, e);
        }
    }

    @Override
    public Product getItemById(Connection connection, int id) {
        ResultSet resultSet;
        Product item = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_ITEM_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                item = parseItem(resultSet);
            }
            LOGGER.debug(Message.ITEM_OBTAINED);
            resultSet.close();
            return item;
        } catch (SQLException e) {
            LOGGER.error(Message.ERR_CANNOT_OBTAIN_ITEM, e);
            throw new DBException(Message.ERR_CANNOT_OBTAIN_ITEM, e);
        }
    }

    @Override
    public Product get(int id) {
        return null;
    }

    @Override
    public int create(Product entity) {
        return 0;
    }

    private Product parseItem(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(ID));
        product.setName(resultSet.getString(PRODUCT_NAME));
        product.setPrice(new BigDecimal(String.valueOf(resultSet.getBigDecimal(PRODUCT_PRICE))));
        product.setQuantity(resultSet.getInt(PRODUCT_QUANTITY));
        product.setImage(resultSet.getString(PRODUCT_IMAGE));

        return product;
    }

    private List<Product> parseItemList(ResultSet resultSet) throws SQLException {
        List<Product> items = new ArrayList<>();
        while (resultSet.next()) {
            items.add(parseItem(resultSet));
        }
        return items;
    }
}
