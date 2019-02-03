package com.epam.polosmak.task3.map;

public class StringHashSum {

    private String string;

    public StringHashSum(String string) {
        this.string = string;
    }

    public StringHashSum() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        int len = string.length() > 4 ? 4 : string.length();
        for (int i = 0; i < len; i++) {
            sum += (int) string.charAt(i);
        }
        return sum;
    }

    @Override
    public String toString() {
        return "StringHashSum{" + "string=" + string + ", hashCode= " + hashCode() + '}';
    }
}