package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.CateringItem;
import com.techelevator.view.Bank;
import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/*
    This class should encapsulate all the functionality of the Catering system, meaning it should
    contain all the "work"
 */
public class CateringSystem {

    private Menu menu;
    private Cart cart;
    private Bank bank;
    private Inventory inventory;

    public CateringSystem(Menu menu, Cart cart, Bank bank, Inventory inventory) {
        this.menu = menu;
        this.cart = cart;
        this.bank = bank;
        this.inventory = inventory;
    }

    public int userSelectedNumber(String input) {
        int number = Integer.parseInt(input);
        if (number > 0 && number < 4) {
            return number;
        }
        menu.showCaseMessage("Invalid entry. Please enter 1, 2, or 3.");
        return 0;
    }

    public String userSelectedAddToCart() throws NullPointerException {
        String cartMessage = "Invalid product code.";
        String productCode = menu.readUserSelection("Please enter a product code to add to cart: ");
        int quantity = Integer.parseInt(menu.readUserSelection("Please enter a quantity for item " + productCode + ": "));
        try {
            cartMessage = cart.addToCart(productCode, quantity);
        } finally {
            return cartMessage;
        }

    }

    public String checkTotalBalance() {
        String totalMessage = "  Current total: $" + cart.getSubtotal();
        if (cart.getSubtotal() > bank.getBalance()) {
            totalMessage += " Insufficient Funds";

        }
        return totalMessage;
    }

    public int findLongestNameLength(List<CateringItem> productIdList) {
        int nameLength = 0;
        for (Map.Entry<String, CateringItem> currentEntry : inventory.getInventoryMap().entrySet()) {
            if (currentEntry.getValue().getName().length() > nameLength && productIdList.contains(currentEntry.getValue())) {
                nameLength = currentEntry.getValue().getName().length();
            }
        }
        return nameLength;


    }


}