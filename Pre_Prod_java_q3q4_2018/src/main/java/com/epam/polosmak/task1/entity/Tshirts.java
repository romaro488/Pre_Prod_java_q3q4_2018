package com.epam.polosmak.task1.entity;

import com.epam.polosmak.task4.annotation.Product;

import java.util.Objects;

public class Tshirts extends Sportswear {

    private String size;
    private boolean availabilityOfSleeves;

    public Tshirts(String name, double price, String color, String size, boolean availabilityOfSleeves) {
        super(name, price, color);
        this.size = size;
        this.availabilityOfSleeves = availabilityOfSleeves;
    }

    public Tshirts() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tshirts tshirts = (Tshirts) o;
        return availabilityOfSleeves == tshirts.availabilityOfSleeves &&
                Objects.equals(size, tshirts.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, availabilityOfSleeves);
    }

    @Override
    public String toString() {
        return "Tshirts: " +
                "name = " + getName() +
                ", price = " + getPrice() +
                ", color = " + getColor() +
                ", size = " + size +
                ", availabilityOfSleeves = " + availabilityOfSleeves;
    }

    public String getSize() {
        return size;
    }

    @Product
    public void setSize(String size) {
        this.size = size;
    }

    public boolean isAvailabilityOfSleeves() {
        return availabilityOfSleeves;
    }

    @Product
    public void setAvailabilityOfSleeves(boolean availabilityOfSleeves) {
        this.availabilityOfSleeves = availabilityOfSleeves;
    }
}
