package com.techelevator.filereader;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InventoryFileReader {

    public List<String> readInventory(String inventoryFileName) throws FileNotFoundException {
        File file = new File(inventoryFileName);
        List<String> linesFromFileList = new ArrayList<>();
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                linesFromFileList.add(fileReader.nextLine());
            }
        }
        return linesFromFileList;
    }
}