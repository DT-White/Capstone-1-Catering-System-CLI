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

    public String addToCart(String productId, int quantity) throws NullPointerException {
        String message = "Invalid product code.";
        CateringItem itemToAdd = inventory.findItemById(productId);
        try {
            if (quantity < 1 || quantity > itemToAdd.getQuantity()) {
                return "Invalid quantity. Select available quantity or new item.";
            }
            if (inventory.getInventoryMap().containsKey(productId)) {
                if (cartMap.containsKey(productId)) {
                    cartMap.put(productId, quantity + cartMap.get(productId));
                    message = "Item quantity updated.";
                } else {
                    cartMap.put(productId, quantity);
                    message = "Item added to cart.";
                }
                subtotal += itemToAdd.getPrice() * quantity;
                inventory.quantityLoweredInInventory(productId,quantity);
            }
        } finally {
            return message;
        }
    }

    public List<CateringItem> getCartList() {
        List<CateringItem> cartList = new ArrayList<>();
        for (Map.Entry<String,Integer> currentEntry: cartMap.entrySet()){
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

    public List <String> formatLogMessage() {
       List <String> messageList = new ArrayList<>();
       for (Map.Entry<String,Integer> currentEntry: cartMap.entrySet()){
           CateringItem currentItem = inventory.findItemById(currentEntry.getKey());
           messageList.add(currentEntry.getValue() + " " + currentItem.getName() + " " + currentItem.getProductCode() );
        }
     return messageList;
    }
    public Map<String,Double> getExtendedPrice () {
        Map<String, Double> extendedPriceMap = new HashMap<>();
        for
        (Map.Entry<String, Integer> currentEntry : cartMap.entrySet()) {
            CateringItem cateringItem = inventory.findItemById(currentEntry.getKey());
            extendedPriceMap.put(currentEntry.getKey(), cateringItem.getPrice() * currentEntry.getValue());

        }
        return extendedPriceMap;
    }
}

