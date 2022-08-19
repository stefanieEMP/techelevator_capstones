package com.techelevator;

public class Item {

    private String name;
    private double price;
    private String message;

    public Item(String name, double price, String message) {
        this.name = name;
        this.price = price;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {

        return price;
    }

    public String getMessage() {
        return message;
    }
}
