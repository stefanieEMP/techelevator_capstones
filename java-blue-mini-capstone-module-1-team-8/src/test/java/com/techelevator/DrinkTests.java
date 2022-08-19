package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class DrinkTests {
    Drink drink = new Drink("English Lemonade", 3.5);

    @Test
    public void get_drink_info() {
        drink.getName();
        drink.getPrice();
        drink.getMessage();
        Assert.assertEquals("English Lemonade", drink.getName());
        Assert.assertEquals(3.5, drink.getPrice(), 0.01);
        Assert.assertEquals("Glug Glug, Yum!", drink.getMessage());
    }
}
