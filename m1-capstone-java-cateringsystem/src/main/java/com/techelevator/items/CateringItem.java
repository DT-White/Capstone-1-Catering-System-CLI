package com.techelevator.items;

public abstract class CateringItem {

    private String productCode;
    private String name;
    private double price;
    private int quantity;
    private String itemType;

    public CateringItem(String itemType, String productCode, String name, Double price) {
        this.itemType = itemType;
        this.productCode = productCode;
        this.name = name;
        this.price = price;
        this.quantity = 25;
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
        return Math.round(price * 100.0) / 100.0;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemType() {
        return itemType;
    }
}