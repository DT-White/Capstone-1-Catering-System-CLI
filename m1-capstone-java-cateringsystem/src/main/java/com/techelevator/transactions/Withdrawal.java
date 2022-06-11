package com.techelevator.transactions;

import java.text.DecimalFormat;

public class Withdrawal extends Transaction{

    public Withdrawal( double withdrawalAmount) {
        super("GIVE CHANGE:", withdrawalAmount, 0.00);
    }


}
