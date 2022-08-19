package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ItemTests {

    Item item = new Item("Meow", 1.5, "uwu");

    @Test
    public void get_item_info() {
        Assert.assertEquals("Meow", item.getName());
        Assert.assertEquals(1.5, item.getPrice(), 0.01);
        Assert.assertEquals("uwu", item.getMessage());
    }
}
