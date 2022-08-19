package com.techelevator;

import java.util.Scanner;

public class PurchaseMenu {

    Scanner userInput = new Scanner(System.in);
    Money balance = new Money();
    Inventory inventory;
    Log writeToFile = new Log();
    String itemName;
    int itemQuantity;


    public PurchaseMenu(Inventory inventory) {
        this.inventory = inventory;
    }

    public void startPurchaseMenu() {
        int chosenOption;
        do {
            System.out.println(" ");
            System.out.println("Please select one of the following options!");
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.print("What option would you like? >>>>> ");
            chosenOption = Integer.parseInt(userInput.nextLine());

            if (chosenOption == 1) {
                do {
                    System.out.println(" ");
                    System.out.print("How much would you like to deposit? Please enter whole dollar amounts: ");
                    try {
                        int userDeposit = Integer.parseInt(userInput.nextLine());
                        if (userDeposit > 0) {
                            balance.depositMoney(userDeposit);
                            //Write the amount of money fed to machine to log.txt
                            writeToFile.createNewLogFile("FEED MONEY: ", userDeposit ,balance.getCurrentBalance());
                        } else {
                            System.out.println("Please enter a valid whole number");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid whole number");
                    }
                    System.out.println("Current Money Provided: $" + balance.getCurrentBalance() + "0");
                    System.out.println(" ");
                    System.out.print("Would you like to deposit more? 1 (Yes) OR 2 (No): ");
                    chosenOption = Integer.parseInt(userInput.nextLine());
                } while (chosenOption == 1);
            } else if (chosenOption == 2) {
                System.out.println(" ");
                inventory.getInventoryFileForPurchaseMenu();
                System.out.println(" ");
                System.out.print("Enter the code of the product you would like: ");
                String userSelection = userInput.nextLine();
                if (inventory.getLocations().get(userSelection.toUpperCase()) == null) {
                    System.out.println("This is an invalid code");
                } else {
                    Item item = inventory.getLocations().get(userSelection.toUpperCase());
                    if (inventory.getQuantity().get(item) == 0) {
                        System.out.println("SOLD OUT");
                    } else {
                        if (balance.getCurrentBalance() < item.getPrice()) {
                            System.out.println("Not enough money");
                        } else {
                            // If an item is successfully dispensed, print the item name, cost,
                            // and money remaining as well as the special message for each type of item dependent upon the item being dispensed
                            itemName = item.getName();
                            double itemPrice = item.getPrice();
                            balance.withdrawMoney(itemPrice);
                            itemQuantity = (inventory.getQuantity().get(item) - 1);
                            inventory.getQuantity().put(item, itemQuantity);
                            System.out.println(itemName + " $" + itemPrice + " Balance Remaining: $" + balance.getCurrentBalance());
                            System.out.println(item.getMessage());
                            // Writes to log.txt - Date, item, location, item price, and current balance.
                            writeToFile.createNewLogFile(itemName + " " + userSelection, item.getPrice(),balance.getCurrentBalance());

                            //==================Still working on this=======================//
                            //Sales Report// -- Does it go here? Who knows.......
                            writeToFile.createNewSalesReport(itemName,itemQuantity,  0);

                        }

                    }
                }
            } else if (chosenOption == 3) {
                //Writes the change due and a balance of 0 to log.txt
                writeToFile.createNewLogFile("GIVE CHANGE: ", balance.getCurrentBalance(), 0.00);
                String currentChange = balance.returnChange();
                System.out.println("Your change is: " + currentChange + ", Thank you!");
                balance.withdrawMoney(balance.getCurrentBalance());
                //Working on Bonus Sales report below///////
                //writeToFile.createNewSalesReport(itemName,itemQuantity);

            }
        } while (chosenOption != 3);
    }

}
