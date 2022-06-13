package com.techelevator.transactions;
import com.techelevator.transactions.Sale;
import com.techelevator.transactions.Transaction;
import org.junit.*;

public class SaleTest {

    private Sale sale;

    @Before
    public void setup() {
        sale = new Sale("10 Sparkling Water B1", 1.35, 2.0);
    }

    @Test
    public void transaction_to_string(){
        Assert.assertTrue(sale.toString().contains("10 Sparkling Water B1 $1.35 $2.00"));
    }
}
