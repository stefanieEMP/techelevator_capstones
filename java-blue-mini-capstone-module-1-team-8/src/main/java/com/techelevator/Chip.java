package com.techelevator;

public class Chip extends Item {

    private static final String chipMessage = "Crunch Crunch, Yum!";

    public Chip(String name, double price) {
        super(name, price, chipMessage);
    }
}
