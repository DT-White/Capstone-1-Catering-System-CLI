package com.techelevator;

import com.techelevator.view.Bank;
import org.junit.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankTest {

    private Bank bank;

  @Before

    public void setup(){bank = new Bank();}

            @Test

    public void test_make_change(){ bank.addMoney(35);
                Map<String,Integer> trialMap = new LinkedHashMap<>();
                trialMap.put("10",1);
                trialMap.put("5", 1);
                trialMap.put("Quarter",2);
                trialMap.put("Dime",1);
                trialMap.put("Nickle",1);
                Assert.assertEquals(trialMap,bank.makeChange(19.35));
            }
}
