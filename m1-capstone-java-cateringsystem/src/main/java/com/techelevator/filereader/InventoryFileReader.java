package com.techelevator.filereader;

import com.techelevator.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    public void readSalesReport(LogFileWriter logFileWriter){
        File file = new File("TotalSales.rpt");
        Map<String, Integer> reportItemMap = new HashMap<>();
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String currentLine = fileReader.nextLine();
                if (!currentLine.equals("")) {
                    String[] currentItemArray = currentLine.split("\\|");
                    reportItemMap.put(currentItemArray[0], Integer.parseInt(currentItemArray[1]));
                } else {
                    break;
                }
            }
            logFileWriter.setReportItemMap(reportItemMap);
        } catch (IOException e){}


    }
}