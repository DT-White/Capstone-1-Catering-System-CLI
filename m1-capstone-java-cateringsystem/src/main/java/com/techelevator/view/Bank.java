package com.techelevator.view;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
    private double balance;
    private double changeDue;
    private Map<String, Integer> changeMap;

    public String addMoney(int moneyToAdd) {
        String message = "$" + moneyToAdd + " added successfully";

        if (moneyToAdd <= 0 || moneyToAdd > 500) {
            message = "Amount needs to be between 1-500";
        } else if
        (moneyToAdd + balance > 1500){
            message = "Balance can not be higher than 1500, spend what you have!";
        }
        else{
            balance += moneyToAdd;
        }


        return message;
    }

    public double getBalance() {
        return balance;
    }

    public void removeBalance(double moneyToRemove) {
        balance -= moneyToRemove;
    }

    public Map<String, Integer> makeChange(Double cartTotal) {
        changeDue = balance - cartTotal;
        changeDue = Math.round(changeDue * 100.0) / 100.0;
        changeMap = new LinkedHashMap<>();

        addToChangeMap(50, "50");
        addToChangeMap(20, "20");
        addToChangeMap(10, "10");
        addToChangeMap(5, "5");
        addToChangeMap(1, "1");
        addToChangeMap(.25, "Quarter");
        addToChangeMap(.1, "Dime");
        addToChangeMap(.05, "Nickle");

        return changeMap;
    }

    public void addToChangeMap(double value, String denomination) {
        while (changeDue >= value) {
            changeDue -= value;
            if (changeMap.containsKey(denomination)) {
                changeMap.put(denomination, changeMap.get(denomination) + 1);
            } else changeMap.put(denomination, 1);
        }
    }

}

