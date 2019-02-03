package com.epam.polosmak.task4.util;

import com.epam.polosmak.task1.entity.Sportswear;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SerializationUtil {

    public static void serialize(Object object, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("error, can't write object");
        }
    }

    public static Object deserialize(String fileName) {
        Map<Sportswear, String> map;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            map = (Map<Sportswear, String>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error, class not found");
            map = new HashMap<>();
        }
        return map;
    }
}
