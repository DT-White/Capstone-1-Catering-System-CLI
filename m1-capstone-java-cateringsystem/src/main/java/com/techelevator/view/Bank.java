package com.techelevator.view;

import com.techelevator.filereader.LogFileWriter;
import com.techelevator.transactions.Deposit;
import com.techelevator.transactions.Transaction;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
    private double balance;
    private double changeDue;
    private Map<String, Integer> changeMap;
    private LogFileWriter logFileWriter;

    public Bank(LogFileWriter logFileWriter) {
        this.logFileWriter = logFileWriter;
    }

    public Bank() {
    }

    public String addMoney(int moneyToAdd)  {
        String message = "$" + moneyToAdd + " added successfully";

        if (moneyToAdd <= 0 || moneyToAdd > 500) {
            message = "Amount needs to be between 1-500";
        } else if
        (moneyToAdd + balance > 1500) {
            message = "Balance can not be higher than 1500, spend what you have!";
        } else {
            balance += moneyToAdd;
            logFileWriter.writeToLog(new Deposit(moneyToAdd, balance));
        }


        return message;
    }

    public double getBalance() {
        return Math.round(balance * 100.0) / 100.0;
    }

    public void removeBalance(double moneyToRemove) {
        balance -= moneyToRemove;
    }

    public Map<String, Integer> makeChange(Double cartTotal) {
        changeDue = balance - cartTotal;
        changeMap = new LinkedHashMap<>();

        addToChangeMap(50.0, "Fifties");
        addToChangeMap(20.0, "Twenties");
        addToChangeMap(10.0, "Tens");
        addToChangeMap(5.0, "Fives");
        addToChangeMap(1.0, "Ones");
        addToChangeMap(.25, "Quarters");
        addToChangeMap(.1, "Dimes");
        addToChangeMap(.05, "Nickles");
        changeDue = 0;
        return changeMap;
    }

    private void addToChangeMap(double value, String denomination) {
        while (Math.round(changeDue * 100.0) / 100.0 >= value) {
            changeDue -= value;
            if (changeMap.containsKey(denomination)) {
                changeMap.put(denomination, changeMap.get(denomination) + 1);
            } else changeMap.put(denomination, 1);
        }
    }

}

