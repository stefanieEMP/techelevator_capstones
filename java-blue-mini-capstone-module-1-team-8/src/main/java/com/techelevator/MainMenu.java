package com.techelevator;


import java.util.Scanner;

public class MainMenu {
    Inventory menuOptionOne = new Inventory();
    PurchaseMenu startUpPurchaseMenu = new PurchaseMenu(menuOptionOne);
    Scanner userInput = new Scanner(System.in);

    public MainMenu() {
    }

    public boolean startMainMenu() {
        System.out.println("Welcome to the Vendo-Matic 800!");
        System.out.println(" ");
        System.out.println("Please select one of the following options:");
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        System.out.print("What option would you like? >>>>> ");
        int chosenOption = Integer.parseInt(userInput.nextLine());

        if (chosenOption == 1) {
            menuOptionOne.getInventoryFileForMainMenu();
        } else if (chosenOption == 2) {
            startUpPurchaseMenu.startPurchaseMenu();
        } else {
            return false;
        }
        System.out.println(); // >>spaces between the loop so not so bunched together
        return true;
    }
}

