package com.techelevator;

import com.techelevator.items.CateringItem;

import java.util.HashMap;
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


    public double getSubtotal() {
        return subtotal;
    }
}
