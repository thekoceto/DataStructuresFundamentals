import implementations.MaxHeap;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));
        for (Integer element : elements) {
            maxHeap.add(element);
        }
        System.out.println(maxHeap);
    }
}
