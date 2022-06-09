package com.techelevator.view;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
    private double balance;

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
        double changeDue = balance - cartTotal;
        changeDue = Math.round(changeDue * 100.0) / 100.0;
        Map<String, Integer> changeMap = new LinkedHashMap<>();

        while (changeDue >= 50) {
            changeDue -= 50;
            if (changeMap.containsKey("50")) {
                changeMap.put("50", changeMap.get("50") + 1);
            } else changeMap.put("50", 1);
        }

        while (changeDue >= 20) {
            changeDue -= 20;
            if (changeMap.containsKey("20")) {
                changeMap.put("20", changeMap.get("20") + 1);
            } else changeMap.put("20", 1);
        }
        while (changeDue >= 10) {
            changeDue -= 10;
            if (changeMap.containsKey("10")) {
                changeMap.put("10", changeMap.get("10") + 1);
            } else changeMap.put("10", 1);
        }
        while (changeDue >= 5) {
            changeDue -= 5;
            if (changeMap.containsKey("5")) {
                changeMap.put("5", changeMap.get("5") + 1);
            } else changeMap.put("5", 1);
        }

        while (changeDue >= 1) {
            changeDue -= 1;
            if (changeMap.containsKey("1")) {
                changeMap.put("1", changeMap.get("1") + 1);
            } else changeMap.put("1", 1);
        }
        while (changeDue >= .25) {
            changeDue -= .25;
            if (changeMap.containsKey("Quarter")) {
                changeMap.put("Quarter", changeMap.get("Quarter") + 1);
            } else changeMap.put("Quarter", 1);
        }
        while (changeDue >= .10) {
            changeDue -= .10;
            if (changeMap.containsKey("Dime")) {
                changeMap.put("Dime", changeMap.get("Dime") + 1);
            } else changeMap.put("Dime", 1);
        }
        while (changeDue >= .05) {
            changeDue -= .05;
            if (changeMap.containsKey("Nickle")) {
                changeMap.put("Nickle", changeMap.get("Nickle") + 1);
            } else changeMap.put("Nickle", 1);
        }
        return changeMap;
    }


}

