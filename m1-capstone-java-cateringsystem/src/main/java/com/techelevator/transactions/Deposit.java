package com.techelevator.transactions;

public class Deposit extends Transaction {
    public Deposit(double depositAmount, double totalBalance) {
        super("ADD MONEY:", depositAmount, totalBalance);
    }

}
