package com.epam.polosmak.entity;

import com.epam.polosmak.enums.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order extends Entity {

    private OrderStatus orderStatus;
    private LocalDate localDate;
    private int userId;
    private List<InfoOrderedItem> orderedItems;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<InfoOrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public boolean addOrderedItems(InfoOrderedItem infoOrderedItem) {
        if (orderedItems != null) {
            return orderedItems.add(infoOrderedItem);
        }

        orderedItems = new ArrayList<>();
        return orderedItems.add(infoOrderedItem);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderStatus=" + orderStatus +
                ", localDate=" + localDate +
                ", userId=" + userId +
                ", orderedItems=" + orderedItems +
                '}';
    }
}
