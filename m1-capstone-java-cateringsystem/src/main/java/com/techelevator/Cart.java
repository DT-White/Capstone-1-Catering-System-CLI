package com.techelevator;

import com.techelevator.items.CateringItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<String, Integer> cartMap;
    private double subtotal;
    private Inventory inventory;

    public Cart(Inventory inventory) {
        this.inventory = inventory;
        this.subtotal = 0;
        this.cartMap = new HashMap<>();
    }

    public String addToCart(String productId, int quantity) {
        String message;
        CateringItem itemToAdd = inventory.findItemById(productId);
        if (cartMap.containsKey(productId)) {
            cartMap.put(productId, quantity + cartMap.get(productId));
            message = "Item quantity updated.";
        } else {
            cartMap.put(productId, quantity);
            message = "Item added to cart.";
        }
        subtotal += itemToAdd.getPrice() * quantity;
        inventory.decreaseInventoryQuantity(productId, quantity);
        return message;
    }

    public List<CateringItem> getCartList() {
        List<CateringItem> cartList = new ArrayList<>();
        for (Map.Entry<String, Integer> currentEntry : cartMap.entrySet()) {
            cartList.add(inventory.findItemById(currentEntry.getKey()));
        }
        return cartList;
    }

    public Map<String, Integer> getCartMap() {
        return cartMap;
    }

    public double getSubtotal() {
        return Math.round(subtotal * 100.0) / 100.0;
    }

    private String formatLogMessage(String productId) {
        CateringItem currentItem = inventory.findItemById(productId);
        return cartMap.get(productId) + " " + currentItem.getName() + " " + currentItem.getProductCode();
    }

    public Map<String, Double> getExtendedPriceMap() {
        Map<String, Double> extendedPriceMap = new HashMap<>();
        for
        (Map.Entry<String, Integer> currentEntry : cartMap.entrySet()) {
            CateringItem cateringItem = inventory.findItemById(currentEntry.getKey());
            extendedPriceMap.put(formatLogMessage(currentEntry.getKey()), Math.round(cateringItem.getPrice() * currentEntry.getValue() * 100.0) / 100.0);
        }
        return extendedPriceMap;
    }
}

