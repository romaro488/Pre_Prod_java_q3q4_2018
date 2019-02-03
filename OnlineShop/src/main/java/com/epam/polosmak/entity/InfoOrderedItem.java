package com.epam.polosmak.entity;

import java.math.BigDecimal;

public final class InfoOrderedItem {

    private int orderId;
    private int itemId;
    private int countOfItems;
    private BigDecimal price;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCountOfItems() {
        return countOfItems;
    }

    public void setCountOfItems(int countOfItems) {
        this.countOfItems = countOfItems;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "InfoOrderedItem{" +
                "orderId=" + orderId +
                ", itemId=" + itemId +
                ", countOfItems=" + countOfItems +
                ", price=" + price +
                '}';
    }
}
