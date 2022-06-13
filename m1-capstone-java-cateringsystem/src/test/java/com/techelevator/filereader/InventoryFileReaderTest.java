package com.techelevator.filereader;
import com.techelevator.filereader.InventoryFileReader;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InventoryFileReaderTest {

    private InventoryFileReader inventoryFileReader;

    @Before
    public void setup() {
        inventoryFileReader = new InventoryFileReader();
    }

    @Test
    public void read_from_inventory_file(){
        try {
            List<String> testList = new ArrayList<>();
            testList.add("B|B1|Sparkling Water|1.35");
            Assert.assertEquals(testList, inventoryFileReader.readInventory("testfile.csv"));
        } catch (FileNotFoundException ignore){}
    }

}
