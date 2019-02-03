package com.epam.polosmak.service.dbService;

import com.epam.polosmak.dao.InfoOrderedItemDAO;
import com.epam.polosmak.dao.OrderDAO;
import com.epam.polosmak.dao.tranaction.TransactionManager;
import com.epam.polosmak.entity.*;
import com.epam.polosmak.enums.OrderStatus;
import com.epam.polosmak.service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private InfoOrderedItemDAO orderedItemDAO;

    private TransactionManager transactionManager;

    public OrderServiceImpl(OrderDAO orderDAO, InfoOrderedItemDAO orderedItemDAO, TransactionManager transactionManager) {
        this.orderDAO = orderDAO;
        this.orderedItemDAO = orderedItemDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean createOrder(Cart cart, User user) {
        Order order = createOrderFromCart(user);
        int orderId = transactionManager.execute(connection -> orderDAO.create(connection, order));
        if (orderId != 0) {
            return transactionManager.execute(connection -> orderedItemDAO.createInfoOrderedItems(connection, createListItems(cart.getAllItems(), orderId)));
        }
        return false;
    }

    private Order createOrderFromCart(User user) {
        Order order = new Order();
        order.setLocalDate(LocalDate.now());
        order.setUserId(user.getId());
        order.setOrderStatus(OrderStatus.ACCEPTED);
        user.setSignUp(true);
        order.setId(order.getId());
        return order;
    }

    private List<InfoOrderedItem> createListItems(Map<Product, Integer> items, int orderId) {
        List<InfoOrderedItem> orderedItems = new ArrayList<>();
        items.forEach((key, value) -> orderedItems.add(createInfoOrderedItem(key, value, orderId)));
        return orderedItems;
    }

    private InfoOrderedItem createInfoOrderedItem(Product item, int countOfItem, int orderId) {
        InfoOrderedItem infoOrderedItem = new InfoOrderedItem();
        infoOrderedItem.setItemId(item.getId());
        infoOrderedItem.setCountOfItems(countOfItem);
        infoOrderedItem.setOrderId(orderId);
        infoOrderedItem.setPrice(item.getPrice());
        return infoOrderedItem;
    }
}