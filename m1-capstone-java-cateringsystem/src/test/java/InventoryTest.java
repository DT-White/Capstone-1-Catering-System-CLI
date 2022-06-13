import com.techelevator.Cart;
import com.techelevator.Inventory;
import com.techelevator.items.*;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory();
    }

    @Test
    public void create_each_item_type(){
        Map<String, CateringItem> testMap = new TreeMap<>();
        testMap.put("B1", new Beverage("B1","Sparkling Water", 1.35));
        testMap.put("A1", new Appetizer("A1","Fruit Bowl", 2.50));
        testMap.put("E1", new Entree("E1","Roasted Chicken", 7.85));
        testMap.put("D1", new Dessert("D1","Brownies", 1.15));
        inventory.createItem(new String[]{"B","B1","Sparkling Water","1.35"});
        inventory.createItem(new String[]{"A","A1","Fruit Bowl","2.50"});
        inventory.createItem(new String[]{"E","E1","Roasted Chicken","7.85"});
        inventory.createItem(new String[]{"D","D1","Brownies","1.15"});
        Map<String, CateringItem> inventoryMap = inventory.getInventoryMap();
        for(Map.Entry<String, CateringItem> entry: testMap.entrySet()){
            Assert.assertTrue(inventoryMap.containsKey(entry.getKey()));
        }
    }

    @Test
    public void find_item_by_name(){
        inventory.createItem(new String[]{"B","B1","Sparkling Water","1.35"});
        Assert.assertEquals("B1", inventory.findItemByName("Sparkling Water").getProductCode());
    }

    @Test
    public void find_item_by_id(){
        inventory.createItem(new String[]{"B","B1","Sparkling Water","1.35"});
        Assert.assertEquals("Sparkling Water", inventory.findItemById("B1").getName());
    }

    @Test
    public void decrease_inventory_quantity(){
        inventory.createItem(new String[]{"B","B1","Sparkling Water","1.35"});
        inventory.decreaseInventoryQuantity("B1", 5);
        Assert.assertEquals(20, inventory.findItemById("B1").getQuantity());
    }
}
