package com.techelevator;

public class Application {
    public static void main(String[] args) {
        boolean loop = true;
        MainMenu menu = new MainMenu();
        do {
            loop = menu.startMainMenu();
        } while (loop);
        // the do-while loop will bring user back to menu start unless they choose to exit
    }
}
