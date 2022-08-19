package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class GumTests {
    Gum gum = new Gum("Bubble Pop", 0.75);

    @Test
    public void get_gum_info() {
        gum.getName();
        gum.getPrice();
        gum.getMessage();
        Assert.assertEquals("Bubble Pop", gum.getName());
        Assert.assertEquals(0.75, gum.getPrice(), 0.01);
        Assert.assertEquals("Chew Chew, Yum!", gum.getMessage());
    }
}
