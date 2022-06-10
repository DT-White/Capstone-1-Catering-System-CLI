package com.techelevator.view;

import com.techelevator.CateringSystem;
import com.techelevator.Inventory;
import com.techelevator.items.CateringItem;

import java.util.Map;
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

    public void showSubMenuHeading() {
        System.out.println("  *************************");
        System.out.println("  **      Ordering       **");
        System.out.println("  **        Menu         **");
        System.out.println("  *************************");
        System.out.println();
    }

    public void showMainMenu() {
        System.out.println();
        System.out.println("  (1) Display Catering Items");
        System.out.println("  (2) Order");
        System.out.println("  (3) Quit");
        System.out.println();
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


    public String readUserSelection(String message) {
        System.out.print(message);
        return userInput.nextLine();
    }

    public void showOrderMenu(double accountBalance, double subtotal) {
        System.out.println();
        System.out.println("  Current Account Balance: $" + accountBalance);
        if (subtotal > 0) {
            System.out.println("  Current total: $" + subtotal);
        }
        System.out.println("  (1) Add Money");
        System.out.println("  (2) Select Product");
        System.out.println("  (3) Complete Transaction");
        System.out.println();

    }

    public int addMoneyEntry() {
        System.out.print("Please enter dollar amount (no change) between 1-500: ");
        int dollarAmount = 0;
       // while (dollarAmount < 1 || dollarAmount > 500) {
            try {
                dollarAmount = Integer.parseInt(userInput.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid entry.");


        }
        return dollarAmount;
    }

    public void showCaseMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    public String tryUserSelectedAddToCart(CateringSystem cateringSystem){
        try {
            return cateringSystem.userSelectedAddToCart();
        } catch (NullPointerException e) {
            return "Invalid product code.";
        }
    }

}
