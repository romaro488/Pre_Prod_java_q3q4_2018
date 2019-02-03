package com.epam.polosmak.task3.map;

public class StringHashLength {

    private String string;

    public StringHashLength(String string) {
        this.string = string;
    }

    public StringHashLength() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public int hashCode() {
        return string.length();
    }

    @Override
    public String toString() {
        return "StringHashLength{" + "string=" + string + ", hashCode= " + hashCode() + '}';
    }
}