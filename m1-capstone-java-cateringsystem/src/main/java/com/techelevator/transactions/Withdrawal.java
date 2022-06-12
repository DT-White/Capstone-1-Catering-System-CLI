package com.techelevator.transactions;

public class Withdrawal extends Transaction {

    public Withdrawal(double withdrawalAmount) {
        super("GIVE CHANGE:", withdrawalAmount, 0.00);
    }

}
