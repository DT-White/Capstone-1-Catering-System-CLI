package com.techelevator;

import com.techelevator.items.*;
import java.util.Map;
import java.util.TreeMap;

public class Inventory {
    private Map<String, CateringItem> inventoryMap = new TreeMap<>();


    public void createItem(String[] itemInfo) {
        CateringItem newItem;
        if (itemInfo[0].equalsIgnoreCase("b")) {
            newItem = new Beverage(itemInfo[1], itemInfo[2], Double.parseDouble(itemInfo[3]));

        } else if
        (itemInfo[0].equalsIgnoreCase("a")) {
            newItem = new Appetizer(itemInfo[1], itemInfo[2], Double.parseDouble(itemInfo[3]));

        } else if (itemInfo[0].equalsIgnoreCase("e")) {
            newItem = new Entree(itemInfo[1], itemInfo[2], Double.parseDouble(itemInfo[3]));

        } else {
            newItem = new Dessert(itemInfo[1], itemInfo[2], Double.parseDouble(itemInfo[3]));
        }
            inventoryMap.put(newItem.getProductCode(), newItem);



    }

    public Map<String, CateringItem> getInventoryMap() {
        return inventoryMap;
    }

    public CateringItem findItemById (String productId) {
        for (Map.Entry<String, CateringItem> currentItem : inventoryMap.entrySet()){
            if (currentItem.getValue().getProductCode().equalsIgnoreCase(productId)) {
                return currentItem.getValue();
            }
        }
        return null;
    }
    public void quantityLoweredInInventory(String productId,int quantity) {
        CateringItem itemToDeplete = inventoryMap.get(productId);
        itemToDeplete.setQuantity(itemToDeplete.getQuantity()- quantity);
    }
    public String[] getProductIdList(){
        return inventoryMap.keySet().toArray(new String[0]);
    }

}




