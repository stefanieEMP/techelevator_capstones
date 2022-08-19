package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ChipTests {
    Chip chip = new Chip("Spicy Triangles", 2.5);

    @Test
    public void get_chip_info() {
        chip.getName();
        chip.getPrice();
        chip.getMessage();
        Assert.assertEquals("Spicy Triangles", chip.getName());
        Assert.assertEquals(2.5, chip.getPrice(), 0.01);
        Assert.assertEquals("Crunch Crunch, Yum!", chip.getMessage());
    }
}
