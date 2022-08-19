package com.techelevator;

public class Drink extends Item {

    private static final String drinkMessage = "Glug Glug, Yum!";

    public Drink(String name, double price) {
        super(name, price, drinkMessage);
    }
}
