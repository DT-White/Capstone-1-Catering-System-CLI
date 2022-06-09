package com.techelevator.items;

public class Dessert extends CateringItem{
    public Dessert(String productCode, String name, Double price) {
        super(productCode, name, price);

    }

    @Override
    public String getReminder() {
        return "Coffee goes with dessert.";
    }
}
