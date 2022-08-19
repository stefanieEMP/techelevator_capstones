package com.techelevator;

public class Candy extends Item {

    private static final String candyMessage = "Munch Munch, Yum!";

    public Candy(String name, double price) {
        super(name, price, candyMessage);
    }
}
