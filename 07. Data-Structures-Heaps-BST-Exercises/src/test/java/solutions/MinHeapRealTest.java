package solutions;

import interfaces.Heap;
import model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MinHeapRealTest {
    private MinHeap<Product> minHeap;

    @Before
    public void setUp() {
        this.minHeap = new MinHeap<>();
        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));

        for (Integer element : elements)
            this.minHeap.add(new Product(element));

    }

    @Test
    public void MinHeapTest01 () {
        assertEquals(8, this.minHeap.size());
    }

    @Test
    public void MinHeapTest02 () {
        assertEquals(5, minHeap.peek().getPrice());
    }

    @Test
    public void MinHeapTest03 () {
        Heap<Product> heap = new MinHeap<>();
        heap.add(new Product(13));
        assertEquals(13, heap.peek().getPrice());
    }

    @Test
    public void MinHeapTest1() {
        MinHeap<Product> heap = new MinHeap<>();
        heap.add(new Product(3));
        assertEquals(1, heap.size());
    }

    @Test
    public void MinHeapTest2() {
        MinHeap<Product> heap = new MinHeap<>();
        heap.add(new Product(3));
        assertEquals(3, heap.peek().getPrice());
    }

    @Test
    public void MinHeapTest3() {
        MinHeap<Product> heap = new MinHeap<>();

        heap.add(new Product(3));
        assertEquals(1, heap.size());

        heap.add(new Product(5));
        assertEquals(2, heap.size());

        heap.add(new Product(6));
        assertEquals(3, heap.size());
    }

    @Test
    public void MinHeapTest4() {
        MinHeap<Product> heap = new MinHeap<>();

        heap.add(new Product(3));
        assertEquals(3, heap.peek().getPrice());

        heap.add(new Product(5));
        assertEquals(3, heap.peek().getPrice());

        heap.add(new Product(2));
        assertEquals(2, heap.peek().getPrice());

        heap.add(new Product(1));
        assertEquals(1, heap.peek().getPrice());

        heap.add(new Product(7));
        assertEquals(1, heap.peek().getPrice());

        heap.add(new Product(8));
        assertEquals(1, heap.peek().getPrice());
    }

    @Test
    public void MinHeapTest5() {
        MinHeap<Product> heap = new MinHeap<>();

        heap.add(new Product(3));
        heap.add(new Product(5));

        assertEquals(2, heap.size());
    }

    @Test
    public void MinHeapTest6() {
        MinHeap<Product> heap = new MinHeap<>();

        heap.add(new Product(3));

        assertEquals(3, heap.peek().getPrice());
    }

    @Test(expected = IllegalStateException.class)
    public void MinHeapTest7 () {
        MinHeap<Product> heap = new MinHeap<>();

        heap.peek();
    }
}