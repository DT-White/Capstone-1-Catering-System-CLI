package com.techelevator.transactions;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {
    private String logMessage;
    private LocalDateTime transactionDate;
    private double currentTransactionAmount;
    private double totalBalance;

    public Transaction(String logMessage,double currentTransactionAmount, double totalBalance) {
        this.logMessage = logMessage;
        this.transactionDate = LocalDateTime.now();
        this.currentTransactionAmount = currentTransactionAmount;
        this.totalBalance = totalBalance;
    }
    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return formatDate() + " " + logMessage + " $" + f.format(currentTransactionAmount) + " $" + f.format(totalBalance);

    }
    protected String formatDate () {
//       int year = transactionDate.getYear();
//       int month = transactionDate.getMonthValue();
//       int day = transactionDate.getDayOfMonth();
//       int hour = transactionDate.getHour();
//       int minutes = transactionDate.getMinute();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        return transactionDate.format(formatter);

    }
}
