import com.techelevator.Cart;
import com.techelevator.Inventory;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class CartTest {

    private Inventory inventory;
    private Cart cart;

    @Before
    public void setup() {
        inventory = new Inventory();
        inventory.createItem(new String[]{"B","B1","Sparkling Water", "1.35"});
        cart = new Cart(inventory);
    }

    @Test
    public void add_to_cart_new_item(){
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("B1", 5);
        cart.addToCart("B1", 5);
        Assert.assertEquals(testMap, cart.getCartMap());
    }

    @Test
    public void add_to_cart_increase_quantity(){
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("B1", 10);
        cart.addToCart("B1", 5);
        cart.addToCart("B1", 5);
        Assert.assertEquals(testMap, cart.getCartMap());
    }

    @Test
    public void get_extended_price_map(){
        Map<String, Double> testMap = new HashMap<>();
        testMap.put("10 Sparkling Water B1", 13.50);
        cart.addToCart("B1", 10);
        Assert.assertEquals(testMap, cart.getExtendedPriceMap());
    }



}
