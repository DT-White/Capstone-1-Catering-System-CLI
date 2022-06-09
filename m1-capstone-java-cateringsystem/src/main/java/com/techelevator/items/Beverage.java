package com.techelevator.items;

public class Beverage extends CateringItem{
    public Beverage(String productCode, String name, Double price) {
        super(productCode, name, price);
    }

    @Override
    public String getReminder() {
        return "Don't forget ice.";
    }
}
