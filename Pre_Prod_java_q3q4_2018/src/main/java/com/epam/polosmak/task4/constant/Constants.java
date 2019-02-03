package com.epam.polosmak.task4.constant;

public interface Constants {

    String PRODUCT_LIST = "store";
    String ADD_TO_CART = "add to cart";
    String SHOW_CART = "cart";
    String SHOW_TOTAL_PRICE = "total cost";
    String BUY_PRODUCTS = "buy products";
    String GET_ORDER_BY_DATE = "get order by date";
    String GET_ORDER_BY_NEAREST_DATE = "get order by nearest date";
    String GET_LAST_PRODUCTS = "get last products";
    String ADD_TO_STORE = "add to store";
    String ADD_TO_STORE_REFLECTION = "add to store R";
    String HELP = "help";
    String EXIT = "exit";

    String GET_COUNT = "get count";
    String GET_ITEM = "get item";
    String GET_COUNT_HTTP = "/shop/count";
    String GET_ITEM_HTTP = "/shop/item?get_info";
    int PORT_TCP = 3000;
    int PORT_HTTP = 8080;

    String NUMBER_REGEXP = "^\\d*$";
    String BOOLEAN_REGEXP = "^(true|false)$";
    String MANUAL = "manual";
    String RANDOM = "random";

    String CART = "----------\nCart\n----------";
    String LAST_PRODUCT_IN_CART = "----------\nLastAddedProductsToCart\n----------";
    String ORDER_LIST = "----------\nOrderList\n----------";
    String TOTAL_PRICE = "----------\nTotal Price\n----------\n";
    String PRODUCT_SHOP = "----------\nProducts\n----------";
}
