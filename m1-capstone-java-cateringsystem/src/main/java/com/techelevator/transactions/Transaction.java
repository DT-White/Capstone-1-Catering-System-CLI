package com.techelevator.transactions;

import java.time.LocalDate;

public abstract class Transaction {
    private String logMessage;
    private LocalDate transactionDate;
    private double currentTransactionAmount;
    private double totalBalance;

    public Transaction(String logMessage,double currentTransactionAmount, double totalBalance) {
        this.logMessage = logMessage;
        this.transactionDate = LocalDate.now();
        this.currentTransactionAmount = currentTransactionAmount;
        this.totalBalance = totalBalance;
    }
    @Override
    public String toString() {
        return transactionDate + " " + logMessage + " $" + currentTransactionAmount + " $" + totalBalance;

    }
}
