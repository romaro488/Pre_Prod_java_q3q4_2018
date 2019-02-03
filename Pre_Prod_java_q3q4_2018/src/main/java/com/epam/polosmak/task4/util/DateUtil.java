package com.epam.polosmak.task4.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date convertStringToDate(String stringDate) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            System.err.println("wrong format date.\nUse current date");
            return new Date();
        }
        return date;
    }
}
