package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.view.Menu;

import java.io.FileNotFoundException;

/*
    This class should encapsulate all the functionality of the Catering system, meaning it should
    contain all the "work"
 */
public class CateringSystem {

    public static void main(String[] args) {

        Menu menu = new Menu();
        Inventory inventory = new Inventory();

        InventoryFileReader inventoryFileReader = new InventoryFileReader("cateringsystem.csv");

        try {
            inventoryFileReader.readInventory(inventory);
        } catch (FileNotFoundException e) {
        }
        menu.showWelcomeMessage();
        menu.showMainMenu();
        if (menu.readUserSelection() == 1) {
            menu.showInventory(inventory);
        }


    }
}
