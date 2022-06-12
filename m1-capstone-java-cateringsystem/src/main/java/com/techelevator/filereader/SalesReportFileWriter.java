package com.techelevator.filereader;

import com.techelevator.Cart;
import com.techelevator.Inventory;
import com.techelevator.items.CateringItem;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReportFileWriter {

    private Map<String, Integer> reportItemMap;

    public SalesReportFileWriter() {
        this.reportItemMap = new HashMap<>();
    }

    public void readSalesReport() throws IOException{
        File file = new File("TotalSales.rpt");
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
        } catch (FileNotFoundException ignored) {
        }
    }

    public void writeSalesReport(Cart cart, Inventory inventory) throws FileNotFoundException, IOException {
        File auditFile = new File("TotalSales.rpt");
        try (PrintWriter printWriter = new PrintWriter(auditFile); BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
            updateReportItemMap(cart, inventory);
            double extendedPrice;
            CateringItem currentItem;
            DecimalFormat f = new DecimalFormat("##.00");
            double totalSales = 0;
            for (Map.Entry<String, Integer> reportItem : reportItemMap.entrySet()) {
                currentItem = inventory.findItemByName(reportItem.getKey());
                extendedPrice = Math.round(currentItem.getPrice() * reportItem.getValue() * 100.0) / 100.0;
                totalSales += extendedPrice;
                bufferedWriter.write(reportItem.getKey() + "|" + reportItem.getValue() + "|" + extendedPrice);
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.write("**TOTAL SALES** $" + f.format(totalSales));
        }
    }

    private void updateReportItemMap(Cart cart, Inventory inventory) {
        if (reportItemMap == null) {
            reportItemMap = new HashMap<>();
        }
        for (Map.Entry<String, Integer> cartItem : cart.getCartMap().entrySet()) {
            String itemName = inventory.findItemById(cartItem.getKey()).getName();
            if (reportItemMap.containsKey(itemName)) {
                reportItemMap.put(itemName, cartItem.getValue() + reportItemMap.get(itemName));
            } else {
                reportItemMap.put(itemName, cartItem.getValue());
            }
        }
    }
}
