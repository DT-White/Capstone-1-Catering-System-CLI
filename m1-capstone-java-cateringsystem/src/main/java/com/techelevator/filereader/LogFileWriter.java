package com.techelevator.filereader;

import com.techelevator.Cart;
import com.techelevator.CateringSystem;
import com.techelevator.Inventory;
import com.techelevator.items.CateringItem;
import com.techelevator.transactions.Transaction;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    This class should contain any and all details of access to the log file
 */
public class LogFileWriter {
    private File logFile = new File("Log.txt");
    private Map<String, Integer> reportItemMap;

    public void writeToLog(Transaction transaction) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, true)); BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
            bufferedWriter.write(transaction.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("catch me when I fall");
        }
    }

//    public void writeSalesReport(Cart cart, Inventory inventory) throws FileNotFoundException, IOException{
//        try (PrintWriter printWriter = new PrintWriter(auditFile); BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
//            int purchaseQuantity;
//            double extendedPrice;
//            DecimalFormat f = new DecimalFormat("##.00");
//            for(CateringItem currentItem: cart.getCartList()) {
//                purchaseQuantity = cart.getCartMap().get(currentItem.getProductCode());
//                extendedPrice = Math.round(currentItem.getPrice() * purchaseQuantity * 100.0) / 100.0;
//                bufferedWriter.write(currentItem.getName() + "|" + purchaseQuantity + "|" + f.format(extendedPrice));
//                bufferedWriter.newLine();
//                bufferedWriter.newLine();
//                bufferedWriter.write("**TOTAL SALES** $" + f.format(totalSales));
//            }
//        }
//    }

    public void writeSalesReport(Cart cart, Inventory inventory){
        File auditFile = new File("TotalSales.rpt");
        try (PrintWriter printWriter = new PrintWriter(auditFile); BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
            updateReportItemMap(cart, inventory);
            double extendedPrice;
            CateringItem currentItem;
            DecimalFormat f = new DecimalFormat("##.00");
            double totalSales = 0;
            for(Map.Entry<String,Integer> reportItem: reportItemMap.entrySet()){
                currentItem = inventory.findItemByName(reportItem.getKey());
                extendedPrice = Math.round(currentItem.getPrice() * reportItem.getValue() * 100.0) / 100.0;
                totalSales += extendedPrice;
                bufferedWriter.write(reportItem.getKey() + "|" + reportItem.getValue() + "|" + extendedPrice);
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.write("**TOTAL SALES** $" + f.format(totalSales));
        }
        catch (IOException e){}
    }

    private void updateReportItemMap(Cart cart, Inventory inventory){
        if (reportItemMap == null){
            reportItemMap = new HashMap<>();
        }
        for(Map.Entry<String, Integer> cartItem: cart.getCartMap().entrySet()){
            String itemName = inventory.findItemById(cartItem.getKey()).getName();
            if(reportItemMap.containsKey(itemName)){
                reportItemMap.put(itemName, cartItem.getValue() + reportItemMap.get(itemName));
            } else{
                reportItemMap.put(itemName, cartItem.getValue());
            }
        }
    }

    public void setReportItemMap(Map<String, Integer> reportItemMap) {
        this.reportItemMap = reportItemMap;
    }
}
