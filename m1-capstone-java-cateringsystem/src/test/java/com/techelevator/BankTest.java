package com.techelevator;

import org.junit.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankTest {

    private Bank bank;

    @Before

    public void setup() {
        bank = new Bank();
    }

    @Test

    public void test_make_change() {
        bank.addMoney(100);
        Map<String, Integer> trialMap = new LinkedHashMap<>();
        trialMap.put("Tens", 1);
        trialMap.put("Fives", 1);
        trialMap.put("Ones", 3);
        trialMap.put("Quarters", 1);
        trialMap.put("Dimes", 2);
        Assert.assertEquals(trialMap, bank.makeChange(81.55));
    }

    @Test
    public void add_money() {
        bank.addMoney(10);
        Assert.assertEquals(10.0, bank.getBalance(), .009);
    }

    @Test
    public void remove_balance() {
        bank.addMoney(10);
        bank.removeBalance(5.0);
        Assert.assertEquals(5.0, bank.getBalance(), .009);
    }
}

