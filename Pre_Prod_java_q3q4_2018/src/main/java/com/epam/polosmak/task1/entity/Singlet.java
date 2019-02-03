package com.epam.polosmak.task1.entity;

import com.epam.polosmak.task4.annotation.Product;

import java.util.Objects;

public class Singlet extends Tshirts {

    private String manufacturer;
    private boolean thePresenceOfPockets;

    public Singlet(String name, double price, String color, String manufacturer, boolean thePresenceOfPockets) {
        super(name, price, color, manufacturer, thePresenceOfPockets);
        this.manufacturer = manufacturer;
        this.thePresenceOfPockets = thePresenceOfPockets;
    }

    public Singlet() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Singlet singlet = (Singlet) o;
        return thePresenceOfPockets == singlet.thePresenceOfPockets &&
                Objects.equals(manufacturer, singlet.manufacturer);
    }

    @Override
    public String toString() {
        return "Singlet: " +
                "name = " + getName() +
                ", price = " + getPrice() +
                ", color = " + getColor() +
                ", size = " + getSize() +
                ", availabilityOfSleeves = " + isAvailabilityOfSleeves() +
                " manufacturer = " + manufacturer + '\'' +
                ", thePresenceOfPockets=" + thePresenceOfPockets +
                '.';
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Product
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isThePresenceOfPockets() {
        return thePresenceOfPockets;
    }

    @Product
    public void setThePresenceOfPockets(boolean thePresenceOfPockets) {
        this.thePresenceOfPockets = thePresenceOfPockets;
    }
}

