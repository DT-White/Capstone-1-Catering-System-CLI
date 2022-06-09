package com.techelevator.items;

public class Entree extends CateringItem{
    public Entree(String productCode, String name, Double price) {
        super(productCode, name, price);
    }

    @Override
    public String getReminder() {
        return "Did you remember Dessert?";
    }
}
