package com.techelevator.filereader;

import com.techelevator.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class InventoryFileReader {

    public void readInventory(Inventory inventory) throws FileNotFoundException {
        String inventoryFileName = "cateringsystem.csv";
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