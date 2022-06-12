package com.techelevator;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
    private double balance;
    private double changeDue;
    private Map<String, Integer> changeMap;

    public Bank() {
    }

    public void addMoney(int moneyToAdd) {
        balance += moneyToAdd;
    }

    public double getBalance() {
        return Math.round(balance * 100.0) / 100.0;
    }

    public double removeBalance(double moneyToRemove) {
        balance -= moneyToRemove;
        return balance;
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

