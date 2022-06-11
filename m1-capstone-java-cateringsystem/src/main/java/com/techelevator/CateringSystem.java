package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.filereader.LogFileWriter;
import com.techelevator.items.CateringItem;
import com.techelevator.transactions.Sale;
import com.techelevator.transactions.Withdrawal;
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
    private LogFileWriter logFileWriter;

    public CateringSystem(Menu menu, Cart cart, Bank bank, Inventory inventory, LogFileWriter logFileWriter) {
        this.menu = menu;
        this.cart = cart;
        this.bank = bank;
        this.inventory = inventory;
        this.logFileWriter = logFileWriter;
    }

    public int checkForValidMenuSelection(String input) throws NumberFormatException{
        int number = 0;
        try {
            number = Integer.parseInt(input);
            if (number > 0 && number < 4) {
                return number;
            }
            menu.showCaseMessage("Invalid entry. Please enter 1, 2, or 3.");
        } finally {
            return number;
        }
    }

    public String userSelectedAddToCart() throws NumberFormatException {
        String cartMessage = "Invalid product code.";
        String productCode = menu.readUserSelection("Please enter a product code to add to cart: ");
        int quantity;
        try {
            quantity = Integer.parseInt(menu.readUserSelection("Please enter a quantity for item " + productCode + ": "));
        } finally {
        }
        cartMessage = cart.addToCart(productCode, quantity);
        return cartMessage;
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

    public String returnChange() {
        Map<String, Integer> changeMap = bank.makeChange(cart.getSubtotal());
        String changeString = " You received";
        String[] stringParts = new String[changeMap.size()];
        int i = 0;
        for (Map.Entry<String, Integer> denomination : changeMap.entrySet()) {
            stringParts[i] = " (" + denomination.getValue() + ") " + denomination.getKey();
            i++;
        }
        changeString += stringParts[0];
        for (int j = 1; j < stringParts.length; j++) {
            changeString += "," + stringParts[j];
        }
        changeString += " in change";
        return changeString;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void completeTransaction(InventoryFileReader inventoryFileReader, LogFileWriter logFileWriter){
        menu.showCart(inventory, cart, this);
        menu.showOrderTotal(cart);
        menu.showChange(this);
        for (Map.Entry<String, Double> currentLogEntry : cart.getExtendedPriceMap().entrySet()) {
            logFileWriter.writeToLog(new Sale(currentLogEntry.getKey(), currentLogEntry.getValue(), bank.removeBalance(currentLogEntry.getValue())));
        }
        logFileWriter.writeToLog(new Withdrawal(bank.getBalance()));
        bank.removeBalance(bank.getBalance());
        inventoryFileReader.readSalesReport(logFileWriter);
        logFileWriter.writeSalesReport(cart, inventory);
        cart = new Cart(inventory);
        setCart(cart);
    }

    public int checkForValidMoneyEntry() {
        int dollarAmount = 0;
        try {
            dollarAmount = Integer.parseInt(menu.readUserSelection("Please enter dollar amount (no change) between 1-500: "));

        } catch (NumberFormatException e) {
            menu.showCaseMessage("Invalid entry.");
        }
        return dollarAmount;
    }
}