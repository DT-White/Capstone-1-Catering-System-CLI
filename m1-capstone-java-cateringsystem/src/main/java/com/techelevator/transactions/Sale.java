package com.techelevator.transactions;

import java.util.Map;

public class Sale extends  Transaction {

    public Sale(String logMessage, double currentTransactionAmount, double totalBalance) {
        super(logMessage, currentTransactionAmount, totalBalance);
    }
}