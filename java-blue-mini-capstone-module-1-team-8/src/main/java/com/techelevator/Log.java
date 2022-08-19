package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Log {
    private String fileName = "log.txt";
    private File logTxtFile = new File(fileName);
    private String salesFileName = "salesLog.txt";
    private File salesTxtFile = new File(salesFileName);
    private String currentTime;

    public String getCurrentTime() {
        currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a"));
        return currentTime;
    }

    public String createNewLogFile(String result, double balanceBefore, double balanceAfter){
        String logEvent = getCurrentTime() + " " + result  + " $"+ balanceBefore + " $" + balanceAfter;

        try(PrintWriter writeToFile = new PrintWriter(new FileWriter(logTxtFile,true))){
            writeToFile.println(logEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return logEvent;
    }

//    public String createNewSalesReport(Map<Item, Integer> quantity){
//        String salesLog = quantity + "|"; // + 5//numberOfSnacksSold;
//
//        try(PrintWriter writeToFile = new PrintWriter(new FileWriter(salesTxtFile,true))){
//            writeToFile.println(salesLog);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return salesLog;
//    }

    // third parameter is not working. Still need to figure it out.
    public String createNewSalesReport(String itemName, int itemQuantity,int totalSales) {
        String salesLog = itemName + "|"+  itemQuantity + "\n" + "$" + totalSales;

        try(PrintWriter writeToFile = new PrintWriter(new FileWriter(salesTxtFile,true))){
            writeToFile.println(salesLog);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return salesLog;

    }
}
