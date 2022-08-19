package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Inventory {

    private int stockLevel = 5;
    private Map<String, Item> locations = new TreeMap<>();
    private Map<Item, Integer> quantity = new HashMap<>();

    public Map<String, Item> getLocations() {
        return locations;
    }

    public Map<Item, Integer> getQuantity() {
        return quantity;
    }

    public Inventory() {
        mapsForData();
    }

    public void mapsForData() {
        try (Scanner fileInput = new Scanner(new File("vendingmachine.csv"))) {
            while (fileInput.hasNextLine()) {
                String lineOfText = fileInput.nextLine();
                String[] data = lineOfText.split("\\|");
                if (data[3].equalsIgnoreCase("Chip")) {
                    Chip chip1 = new Chip(data[1], Double.parseDouble(data[2]));
                    locations.put(data[0], chip1);
                    quantity.put(chip1, stockLevel);
                } else if (data[3].equalsIgnoreCase("Candy")) {
                    Candy candy1 = new Candy(data[1], Double.parseDouble(data[2]));
                    locations.put(data[0], candy1);
                    quantity.put(candy1, stockLevel);
                } else if (data[3].equalsIgnoreCase("Drink")) {
                    Drink drink1 = new Drink(data[1], Double.parseDouble(data[2]));
                    locations.put(data[0], drink1);
                    quantity.put(drink1, stockLevel);
                } else {
                    Gum gum1 = new Gum(data[1], Double.parseDouble(data[2]));
                    locations.put(data[0], gum1);
                    quantity.put(gum1, stockLevel);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getInventoryFileForMainMenu() {
//      >>> With the maps, we don't need to read directly from the file anymore, instead we use the map data
        for (Item idk : quantity.keySet()) {
            System.out.println("Item: " + alignText(idk.getName(), 25) + " Available: " + quantity.get(idk));
        }
    }

    public void getInventoryFileForPurchaseMenu() {
        for (String location : locations.keySet()) {
            System.out.println(location + ": " + alignText(locations.get(location).getName(), 25) + "$" + locations.get(location).getPrice());
        }
    }

    public String alignText(String name, int length) {
        for (int i = name.length(); i < length; i++) {
            name += "-";
        }
        return name; // >>this makes it pretty
    }
}




