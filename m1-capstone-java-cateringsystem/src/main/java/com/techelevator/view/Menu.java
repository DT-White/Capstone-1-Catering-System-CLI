package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.items.CateringItem;

import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/*
 * This is the only class that should have any usage of System.out or System.in
 *
 * Usage of System.in or System.out should not appear ANYWHERE else in your code outside of this class.
 *
 * Work to get input from the user or display output to the user should be done in this class, however, it
 * should include no "work" that is the job of the catering system.
 */
public class Menu {

    private static final Scanner userInput = new Scanner(System.in);

    public void showWelcomeMessage() {
        System.out.println("  *************************");
        System.out.println("  **     Weyland Corp.   **");
        System.out.println("  **      Catering       **");
        System.out.println("  *************************");
        System.out.println();
    }

    public void showMainMenu() {
        System.out.println();
        System.out.println("  (1) Display Catering Items");
        System.out.println("  (2) Order");
        System.out.println("  (3) Quit");
        System.out.println();
        System.out.print("Please enter selection by number: ");
    }

    public void showInventory(Inventory inventory) {
        String space = " ";
        int nameLength = 0;

        // Find the longest name in the inventory for equal spacing
        for (Map.Entry<String, CateringItem> currentEntry : inventory.getInventoryMap().entrySet()) {
            if (currentEntry.getValue().getName().length() > nameLength) {
                nameLength = currentEntry.getValue().getName().length();
            }
        }
        System.out.println();
        System.out.println("Product Code     Description" + space.repeat(nameLength - 7) + "Qty      Price");

        for (Map.Entry<String, CateringItem> currentEntry : inventory.getInventoryMap().entrySet()) {
            CateringItem currentItem = currentEntry.getValue();
            System.out.print(space + currentItem.getProductCode());
            System.out.print("              " + currentItem.getName());
            System.out.print(space.repeat((nameLength + 4) - currentItem.getName().length()) + currentItem.getQuantity());
            String quantity = Integer.toString(currentItem.getQuantity());
            System.out.println(space.repeat(9 - quantity.length()) + "$" + currentItem.getPrice());
        }
    }

    public int readUserSelection() {
        int userSelection = 0;
        while (userSelection > 4 || userSelection < 1) {
            try {
                userSelection = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid command entry. Please enter 1, 2, or 3.");
            }
        }
        return userSelection;
    }

    public void showOrderMenu(double accountBalance) {
        System.out.println("  *************************");
        System.out.println("  **      Ordering       **");
        System.out.println("  **        Menu         **");
        System.out.println("  *************************");
        System.out.println();
        System.out.println("  (1) Add Money");
        System.out.println("  (2) Select Product");
        System.out.println("  (3) Complete Transaction");
        System.out.println("  Current Account Balance: $" + accountBalance);
        System.out.println();
        System.out.print("Please enter selection by number: ");

    }

    public int addMoneyEntry() {
        System.out.print("Please Enter dollar amount (no change) between 1-500: ");
        int dollarAmount = 0;
       // while (dollarAmount < 1 || dollarAmount > 500) {
            try {
                dollarAmount = Integer.parseInt(userInput.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid numerical entry.");


        }
        return dollarAmount;
    }

    public void showCaseMessage(String message) {
        System.out.println(message);
    }
}
