package com.epam.polosmak.message;

public class Message {

    public static final String USER_START_CREATING = "User start creating";


    //Order dao
    public static final String ORDER_CREATE_SUCCESS = "Order create success";
    public static final String ORDERED_ITEM_CREATE_SUCCESS = "Ordered item create success";

    public static final String ERR_CANNOT_CREATE_ORDER = "Cannot create order.";
    public static final String ERR_CANNOT_CREATE_ORDERED_ITEM = "Cannot create ordered item.";


    //Item dao
    public static final String ITEM_OBTAINED = "Item object was obtained from database.";
    public static final String ERR_CANNOT_OBTAIN_ITEM = "Cannot obtain item from database.";

}
