package com.techelevator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Money {

    private double currentBalance = 0;


    public Money() {
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double depositMoney(double depositMoney) {
        currentBalance = currentBalance + depositMoney;
        return currentBalance;
    }

    public double withdrawMoney(double withdrawMoney) {
        currentBalance = currentBalance - withdrawMoney;
        return getCurrentBalance();
    }


    public String returnChange() {
        Map<Double, String> change = new LinkedHashMap<>();
        change.put(0.25, "Quarters");
        change.put(0.1, "Dimes");
        change.put(0.05, "Nickels");

        String output = "";

        Set<Double> keys = change.keySet();
        for (Double someName : keys) {
            String changeString = change.get(someName);
            int quantity = 0;

            while (currentBalance / someName >= 1) {
                quantity++;
                currentBalance -= someName;
            }
            if (quantity > 0) {
                output += quantity + " " + changeString + " ";
            }
        }
        return output.trim();
    }
}
