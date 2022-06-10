package com.techelevator.transactions;

public class Withdrawal extends Transaction{

    public Withdrawal( double withdrawalAmount, double totalBalance) {
        super("GIVE CHANGE:", withdrawalAmount, totalBalance);
    }
}
