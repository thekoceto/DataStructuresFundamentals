package core;

import model.Message;
import model.TextMessage;
import org.junit.Before;
import org.junit.Test;
import shared.DataTransferSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PerformanceTest {

    @Test
    public void createAndDeleteMessage_with_10000_entries() {
        DataTransferSystem system = new MessagingSystem();

        long start = System.currentTimeMillis();

        List<Integer> toShuffle = new ArrayList<>();

        for (int i = 0; i < 10000; i++)
            toShuffle.add(i);
        Collections.shuffle(toShuffle);

        for (int i = 0; i < 10000; i++)
            system.add(new TextMessage(toShuffle.get(i), "test_text" + i ));

        for (int i = 0; i < 10000; i++)
            assertEquals(i, system.deleteLightest().getWeight());

        long stop = System.currentTimeMillis();

        assertTrue(stop - start <= 450);

    }
}
