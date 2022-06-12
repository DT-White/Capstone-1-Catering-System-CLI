package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.filereader.LogFileWriter;
import com.techelevator.filereader.SalesReportFileWriter;
import com.techelevator.items.CateringItem;
import com.techelevator.transactions.Deposit;
import com.techelevator.transactions.Sale;
import com.techelevator.transactions.Transaction;
import com.techelevator.transactions.Withdrawal;
import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;


public class CateringSystem {

    private LogFileWriter logFileWriter;
    private Bank bank;
    private InventoryFileReader inventoryFileReader;
    private Inventory inventory;
    private Cart cart;
    private Menu menu;
    private SalesReportFileWriter salesReportFileWriter;

    public CateringSystem(Menu menu) {
        this.inventory = new Inventory();
        this.cart = new Cart(inventory);
        this.bank = new Bank();
        this.logFileWriter = new LogFileWriter();
        this.inventoryFileReader = new InventoryFileReader();
        this.menu = menu;
        this.salesReportFileWriter = new SalesReportFileWriter();
    }

    public void populateInventory() {
        try {
            inventoryFileReader.readInventory(inventory);
        } catch (FileNotFoundException e) {
            menu.showCaseMessage("Inventory file not found.");
        }
    }

    public int checkForValidMenuSelection(String input) {
        int number = 0;
        try {
            number = Integer.parseInt(input);
            if (number > 0 && number < 4) {
                return number;
            }
            menu.showCaseMessage("Invalid entry. Please enter 1, 2, or 3.");
        } catch (NumberFormatException | NullPointerException e) {
            menu.showCaseMessage("Invalid entry. Please enter 1, 2, or 3.");
        }
        return number;
    }

    public void displayCateringItems() {
        int nameLength = 0;
        for (Map.Entry<String, CateringItem> currentEntry : inventory.getInventoryMap().entrySet()) {
            if (currentEntry.getValue().getName().length() > nameLength) {
                nameLength = currentEntry.getValue().getName().length();
            }
        }
        menu.showInventory(inventory, nameLength);
    }

    public void callSubMenu() {
        String totalMessage = "";
        if (cart.getSubtotal() > 0) {
            totalMessage += "\n  Current total: $" + cart.getSubtotal();
            if (cart.getSubtotal() > bank.getBalance()) {
                totalMessage += " Insufficient Funds";
            }
        }
        menu.showOrderMenu(bank.getBalance(), totalMessage);
    }

    public String checkForValidMoneyEntry(String input) {
        int dollarAmount = 0;
        try {
            dollarAmount = Integer.parseInt(input);
        } catch (NullPointerException | NumberFormatException e) {
            menu.showCaseMessage("Invalid money entry.");
        }
        String message = "$" + dollarAmount + " added successfully";
        if (dollarAmount <= 0 || dollarAmount > 500) {
            message = "Amount needs to be between 1-500";
        } else if (dollarAmount + bank.getBalance() > 1500) {
            message = "Balance can not be higher than 1500, spend what you have!";
        } else {
            bank.addMoney(dollarAmount);
            writeLogEntry(new Deposit(dollarAmount, bank.getBalance()));
        }
        return message;
    }

    public String checkForValidAddToCart() {
        String message = "Invalid product code.";
        String productId = menu.readUserInput("Please enter a product code to add to cart: ").toUpperCase();
        if (inventory.getInventoryMap().containsKey(productId)) {
            CateringItem itemToAdd = inventory.findItemById(productId);
            int quantity;
            try {
                quantity = Integer.parseInt(menu.readUserInput("Please enter a quantity for item " + productId + ": "));

                if (quantity > 0 && quantity <= itemToAdd.getQuantity() && (itemToAdd.getPrice() * quantity) + cart.getSubtotal() <= bank.getBalance()) {
                    message = cart.addToCart(productId, quantity);
                } else {
                    message = "Insufficient funds or invalid quantity. Check availability and try again.";
                }
            } catch (NumberFormatException e) {
                message = "Invalid quantity.";
            }
        }
        return message;
    }

    private void displayCart() {
        int nameLength = 0;
        for (CateringItem currentItem : cart.getCartList()) {
            if (currentItem.getName().length() > nameLength) {
                nameLength = currentItem.getName().length();
            }
        }
        menu.showCart(cart, nameLength);
    }

    public void returnChange() {
        String changeString = " You received";
        Map<String, Integer> changeMap = bank.makeChange(cart.getSubtotal());
        if (changeMap.size() > 0) {
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
            changeString += " in change.";
        } else {
            changeString += " no change.";
        }
        menu.showCaseMessage(changeString);
    }

    private void resetCart() {
        cart = new Cart(inventory);
    }

    public void completeTransaction() {
        if (cart.getCartList().size() > 0) {
            if (bank.getBalance() >= cart.getSubtotal()) {
                displayCart();
                menu.showOrderTotal(cart);
                returnChange();
                for (Map.Entry<String, Double> currentLogEntry : cart.getExtendedPriceMap().entrySet()) {
                    writeLogEntry(new Sale(currentLogEntry.getKey(), currentLogEntry.getValue(), bank.removeBalance(currentLogEntry.getValue())));
                }
                writeLogEntry(new Withdrawal(bank.getBalance()));
                bank.removeBalance(bank.getBalance());
                writeToSalesReport();
                cart = new Cart(inventory);
                resetCart();
            } else {
                menu.showCaseMessage("Insufficient funds.");
            }
        } else {
            menu.showCaseMessage("Cart is empty.");
        }
    }

    private void writeLogEntry(Transaction transaction) {
        try {
            logFileWriter.writeToLog(transaction);
        } catch (FileNotFoundException e) {
            menu.showCaseMessage("Log file not found");
        } catch (IOException e) {
            menu.showCaseMessage("A problem occurred");
        }
    }

    private void writeToSalesReport() {
        try {
            salesReportFileWriter.readSalesReport();
        } catch (FileNotFoundException e) {
            menu.showCaseMessage("Sales report file not found.");
        } catch (IOException e) {
            menu.showCaseMessage("A problem occurred.");
        }
        try {
            salesReportFileWriter.writeSalesReport(cart, inventory);
        } catch (FileNotFoundException e) {
            menu.showCaseMessage("Sales report file not found.");
        } catch (IOException e) {
            menu.showCaseMessage("A problem occurred.");
        }
    }
}