package com.techelevator.filereader;

import com.techelevator.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    This class should contain any and all details of access to the inventory file
 */
public class InventoryFileReader {

    private String inventoryFileName;

    public InventoryFileReader(String inventoryFileName) {
        this.inventoryFileName = inventoryFileName;

    }

    public void readInventory(Inventory inventory) throws FileNotFoundException {
        File file = new File(inventoryFileName);
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {

                String currentLine = fileReader.nextLine();
                String[] currentItemArray = currentLine.split("\\|");
                inventory.createItem(currentItemArray);
            }
        }
    }
}