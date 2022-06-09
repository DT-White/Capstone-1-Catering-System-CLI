package com.techelevator.items;

public class Appetizer extends CateringItem{

    public Appetizer(String productCode, String name, Double price) {
        super(productCode, name, price);
    }

    @Override
    public String getReminder() {
        return "You might need extra plates.";
    }
}
