package core;

import model.HardwareOrder;
import model.SampleFile;
import org.junit.Test;
import shared.FileManager;
import shared.Shop;

import static org.junit.Assert.assertTrue;

public class PerformanceTest {

    @Test
    public void createAndDeleteFiles_with_10000_entries() {
        FileManager fileManager = new FileExplorer();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++)
            fileManager.addInDirectory(i + 1, new SampleFile(i+2, "test_name"));

        for (int i = 9999; i > 1; i--)
            fileManager.deleteFile(new SampleFile(i, "test_name"));

        long stop = System.currentTimeMillis();
        assertTrue(stop - start <= 2500);

    }

    @Test
    public void createAndMoveFiles_with_10000_entries() {
        FileManager fileManager = new FileExplorer();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++)
            fileManager.addInDirectory(i + 1, new SampleFile(i+2, "test_name"));

        for (int i = 9999; i >= 1; i--)
            fileManager.move(new SampleFile(i + 1, "test_name"),
                             new SampleFile(i, "test_name"));

        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;
        assertTrue(elapsedTime <= 3100);

    }

    @Test
    public void createAndSwapOrder_with_10000_entries() {
        Shop shop = new OnlineShop();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++)
            shop.add(new HardwareOrder(i, "hardware_order"));


        for (int i = 0; i < 10000 - 1; i++)
            shop.swap(new HardwareOrder(i, "hardware_order"),
                    new HardwareOrder(i + 1, "hardware_order"));

        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;
        assertTrue(elapsedTime <= 450);

    }

}
