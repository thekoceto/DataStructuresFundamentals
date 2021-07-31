package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private List<E> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }
    //   0    1    2    3    4    5    6
    // [ b ][ l ][ r ][ll ][lr ][rl ][rr ]
    // l = ()
    // l = ()
    @Override
    public void add(E element) {
        this.elements.add(element);
        this.HeapifyUp(this.size()-1);
    }

    private void HeapifyUp(int index) {
        while (isLess(index, parentIndex(index)))
            this.swap(index, index = parentIndex(index));
    }

    private int parentIndex(int index) {
        return (index-1)/2;
    }

    private boolean isLess(int index1, int index2) {
        return this.elements.get(index1).compareTo(this.elements.get(index2)) > 0;
    }

    private void swap(int index1, int index2) {
        // swap with a child having greater value
        E temp = this.elements.get(index1);
        elements.set(index1, this.elements.get(index2));
        elements.set(index2, temp);
    }

    @Override
    public E peek() {
        if (this.elements.isEmpty())
            throw new IllegalStateException("There are no element to peek");

        return this.elements.get(0);
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        this.elements.forEach(element -> output.append(element).append(' '));
        return output.toString().trim();
    }
}
