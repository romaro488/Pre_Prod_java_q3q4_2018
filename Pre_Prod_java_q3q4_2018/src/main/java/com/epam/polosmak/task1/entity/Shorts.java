package com.epam.polosmak.task1.entity;

import com.epam.polosmak.task4.annotation.Product;

import java.util.Objects;

public class Shorts extends Sportswear {

    private String landing;

    public Shorts(String name, double price, String color, String landing) {
        super(name, price, color);
        this.landing = landing;
    }

    public Shorts() {
    }

    @Override
    public String toString() {
        return "Shorts: " +
                "name = " + getName() +
                ", price = " + getPrice() +
                ", color = " + getColor() +
                ", landing = " + landing +
                '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Shorts shorts = (Shorts) o;
        return Objects.equals(landing, shorts.landing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), landing);
    }

    public String getLanding() {
        return landing;
    }

    @Product
    public void setLanding(String landing) {
        this.landing = landing;
    }
}
