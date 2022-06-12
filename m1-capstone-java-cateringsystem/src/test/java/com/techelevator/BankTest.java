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
    public void test_make_change2() {
        bank.addMoney(500);
        Map<String, Integer> trialMap = new LinkedHashMap<>();
        trialMap.put("Fifties", 7);
        trialMap.put("Tens", 1);
        trialMap.put("Fives", 1);
        trialMap.put("Quarters", 3);
        trialMap.put("Dimes", 1);
        Assert.assertEquals(trialMap, bank.makeChange(134.15));
    }
}

