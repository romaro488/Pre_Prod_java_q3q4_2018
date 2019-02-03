package com.epam.polosmak.task4.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LocaleUtil {

    private static final String LANGUAGE = "ru";
    private static final String COUNTRY = "RU";

    public static void setLocal(String local) {

        Map<String, Locale> localeMap = new HashMap<>();
        localeMap.put("0", Locale.ENGLISH);
        localeMap.put("1", new Locale(LANGUAGE, COUNTRY));
        Locale.setDefault(localeMap.getOrDefault(local, Locale.ENGLISH));
    }
}
