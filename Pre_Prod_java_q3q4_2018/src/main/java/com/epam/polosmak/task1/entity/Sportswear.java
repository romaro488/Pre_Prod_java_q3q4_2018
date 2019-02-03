package com.epam.polosmak.task1.entity;

import com.epam.polosmak.task4.annotation.Product;

import java.io.Serializable;
import java.util.Objects;

public class Sportswear implements Serializable {

    private String name;
    private double price;
    private String color;

    public Sportswear() {
    }

    public Sportswear(String name, double price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sportswear that = (Sportswear) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, color);
    }

    @Override
    public String toString() {
        return "Sportswear: " +
                "name = " + name +
                ", price = " + price +
                ", color = " + color;
    }

    public String getName() {
        return name;
    }

    @Product
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    @Product
    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    @Product
    public void setColor(String color) {
        this.color = color;
    }
}
