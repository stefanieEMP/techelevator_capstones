package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransfersService;

import java.math.BigDecimal;
import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final AccountService accountService = new AccountService(API_BASE_URL);
    private final TransfersService transfersService = new TransfersService(API_BASE_URL);

    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        // need token from logged in user to find their account to access balance
        BigDecimal balance = accountService.getAccountBalance(currentUser);
        System.out.println("Your current balance is: $" + balance);
	}

	private void viewTransferHistory() {
        List<Transfers> transfersList = transfersService.getTransferHistory(currentUser);
        for (Transfers transfers : transfersList) {
            String output = Integer.toString(transfers.getTransferId());
            if (transfers.getFromUsername().equalsIgnoreCase(currentUser.getUser().getUsername())) {
                output += " To: "+transfers.getToUsername();
            } else {
                output += " From: "+transfers.getFromUsername();
            }
            output += " $"+transfers.getTransferAmount();
            System.out.println(output);
        }
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
        List<Account> accountList = accountService.listAccounts(currentUser);
        // remove current user
        long currentuserIndex = -1;

        // present user list
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUserId() == currentUser.getUser().getId()) {
               currentuserIndex = i;
               continue;
            }
            int displayIndex = i+1;
            if (currentuserIndex != -1) {
                displayIndex--;
            }
            System.out.println(displayIndex + ": " + accountList.get(i).getUsername());
        }
        int selection = consoleService.promptForInt("Select user: ") - 1;
        if (selection >= currentuserIndex) {
           // add 1 to account for skipping current user
           selection++;
        }
        if (selection < 0 || selection >= accountList.size()) {
            System.out.println("Invalid selection");
            return;
        }

        // get balance
        BigDecimal balance = accountService.getAccountBalance(currentUser);
        System.out.println("Sending money to " + accountList.get(selection).getUsername());
        System.out.println("Your current balance: $" + balance);
        BigDecimal amountToSend = consoleService.promptForBigDecimal("How much money would you like to send? ");
        if (balance.compareTo(amountToSend) < 0) {
            System.out.println("You do not have the required balance to send this request.");
            return;
        }
        if (amountToSend.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("You must send a positive amount.");
            return;
        }

        Integer transactionId = transfersService.sendTransaction(currentUser, accountService.getAccount(currentUser).getId(), accountList.get(selection).getId(), amountToSend);

        if (transactionId != null) {
            System.out.println("Your transfer was successful! Your transaction ID is "+transactionId);
        }

		// choose from list of users
        // toUser cannot == currentUser
        // toUser balance increased by amount
        // fromUser balance decreased by amount
        // amount cannot be greater than fromUser balance
        // amount cannot be less than or equal to 0
        // initial status of Approved

		
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
