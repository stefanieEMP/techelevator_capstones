package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTests {

    Money balance;

    @Before
    public void init() {
        balance = new Money();
    }

    @Test
    public void deposit_money() {
        balance.depositMoney(5);
        Assert.assertEquals(5, balance.getCurrentBalance(), 0.01);
    }

    @Test
    public void withdraw_money() {
        balance.depositMoney(5);
        balance.withdrawMoney(4);
        Assert.assertEquals(1, balance.getCurrentBalance(), 0.01);
    }

    @Test
    public void return_change() {
        balance.depositMoney(5);
        Assert.assertEquals("20 Quarters", balance.returnChange());
    }
}
