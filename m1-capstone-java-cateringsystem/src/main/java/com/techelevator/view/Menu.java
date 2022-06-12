package com.techelevator.view;

import com.techelevator.Cart;
import com.techelevator.Inventory;
import com.techelevator.items.CateringItem;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private static final Scanner userInput = new Scanner(System.in);
    private final String SPACE = " ";
    private final DecimalFormat F = new DecimalFormat("##.00");


    public void showWelcomeMessage() {
        System.out.println("  *************************");
        System.out.println("  **     Weyland Corp.   **");
        System.out.println("  **      Catering       **");
        System.out.println("  *************************");
        System.out.println();
    }

    public String readUserInput(String message) {
        String userEntry;
        System.out.println();
        System.out.print(message);
        userEntry = Menu.userInput.nextLine();
        return userEntry;
    }

    public void showInventory(Inventory inventory, int nameLength) {
        showCaseMessage("Product Code     Description" + SPACE.repeat(nameLength - 7) + "Qty      Price");

        for (Map.Entry<String, CateringItem> currentEntry : inventory.getInventoryMap().entrySet()) {
            CateringItem currentItem = currentEntry.getValue();
            System.out.print(SPACE + currentItem.getProductCode());
            System.out.print("              " + currentItem.getName());
            System.out.print(SPACE.repeat((nameLength + 4) - currentItem.getName().length()) + (currentItem.getQuantity() > 0 ? currentItem.getQuantity() : "SOLD OUT"));
            String quantity = (currentItem.getQuantity() > 0 ? Integer.toString(currentItem.getQuantity()) : "SOLD OUT");
            System.out.println(SPACE.repeat(9 - quantity.length()) + "$" + F.format(currentItem.getPrice()));
        }
    }

    public void showSubMenuHeading() {
        System.out.println("  *************************");
        System.out.println("  **      Ordering       **");
        System.out.println("  **        Menu         **");
        System.out.println("  *************************");
        System.out.println();
    }

    public void showOrderMenu(double accountBalance, String totalMessage) {
        System.out.println();
        System.out.println("  Current Account Balance: $" + accountBalance + totalMessage);
        System.out.println("  (1) Add Money");
        System.out.println("  (2) Select Product");
        System.out.println("  (3) Complete Transaction");
    }

    public void showMainMenu() {
        System.out.println();
        System.out.println("  (1) Display Catering Items");
        System.out.println("  (2) Order");
        System.out.println("  (3) Quit");
    }

    public void showCart(Cart cart, int nameLength) {
        for (CateringItem currentItem : cart.getCartList()) {
            int quantity = cart.getCartMap().get(currentItem.getProductCode());
            String extendedPriceFormatted = F.format(currentItem.getPrice() * quantity);
            String individualPriceFormatted = F.format(currentItem.getPrice());
            System.out.print(SPACE + quantity);
            System.out.print(SPACE.repeat(6 - Integer.toString(quantity).length()) + currentItem.getItemType());
            System.out.print(SPACE.repeat(12 - currentItem.getItemType().length()) + currentItem.getName());
            System.out.print(SPACE.repeat((nameLength + 4) - currentItem.getName().length()) + "$" + individualPriceFormatted);
            System.out.print(SPACE.repeat(11 - individualPriceFormatted.length()) + "$" + extendedPriceFormatted);
            System.out.println(SPACE.repeat(11 - extendedPriceFormatted.length()) + currentItem.getReminder());
        }
    }

    public void showCaseMessage(String message) {
        if (message != null) {
            System.out.println();
            System.out.println(message);
        }
    }

    public void showOrderTotal(Cart cart) {
        showCaseMessage(" Total: $" + cart.getSubtotal());
    }

}
