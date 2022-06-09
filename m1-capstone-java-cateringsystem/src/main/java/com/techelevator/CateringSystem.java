package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;

import java.io.FileNotFoundException;

/*
    This class should encapsulate all the functionality of the Catering system, meaning it should
    contain all the "work"
 */
public class CateringSystem {

    public static void main(String[] args) {


        Inventory inventory = new Inventory();

        InventoryFileReader inventoryFileReader = new InventoryFileReader("cateringsystem.csv");

        try {
            inventoryFileReader.readInventory(inventory);
        } catch (FileNotFoundException e) {
        }

    }
}
