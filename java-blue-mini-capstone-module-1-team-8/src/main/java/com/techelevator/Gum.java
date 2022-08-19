package com.techelevator;

public class Gum extends Item {

    private static final String gumMessage = "Chew Chew, Yum!";

    public Gum(String name, double price) {
        super(name, price, gumMessage);
    }
}
