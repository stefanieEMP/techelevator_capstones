package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTests {

    Inventory newInventory;

    @Before
    public void init() {
        newInventory = new Inventory();
    }

    @Test
    public void get_locations() {
        Assert.assertEquals("Potato Crisps", newInventory.getLocations().get("A1").getName());
    }

    @Test
    public void get_quantity() {
        Assert.assertEquals(5, newInventory.getQuantity().get(newInventory.getLocations().get("A1")).intValue());
    }

    @Test
    public void align_text() {
        Assert.assertEquals("hi--------", newInventory.alignText("hi", 10));
    }

}
