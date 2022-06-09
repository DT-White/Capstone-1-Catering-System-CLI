package com.techelevator.items;

/*
    This represents a single catering item in the system
 */
public abstract class CateringItem {



    private String productCode;
    private String name;
    private double price;
    private int quantity = 25;

    public CateringItem(String productCode, String name, Double price) {

        this.productCode = productCode;
        this.name = name;
        this.price = price;

    }

    public String getProductCode() {
        return productCode;
    }

    public abstract String getReminder();


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}