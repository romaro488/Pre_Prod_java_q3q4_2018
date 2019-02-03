package com.epam.polosmak.task7.product;

public class Sportswear implements Product {

    private String name;
    private String color;
    private int price;

    public Sportswear() {
    }

    public Sportswear(String name, String color, int price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sportswear{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                "price='" + price;
    }
}
