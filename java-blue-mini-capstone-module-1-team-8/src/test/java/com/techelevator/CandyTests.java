package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class CandyTests {

    Candy candy = new Candy("Gummy Worms", 1.5);

    @Test
    public void get_candy_info() {
        candy.getName();
        candy.getPrice();
        candy.getMessage();
        Assert.assertEquals("Gummy Worms", candy.getName());
        Assert.assertEquals(1.5, candy.getPrice(), 0.01);
        Assert.assertEquals("Munch Munch, Yum!", candy.getMessage());
    }
}
