package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.HeapifyDown(this.size()-1);
    }

    private void HeapifyDown(int index) {
        while (index > 0 && isLess(index, index-1)){
            this.swap(index, index-1);
            index--;
        }
    }

    private boolean isLess(int index1, int index2) {
        return this.elements.get(index1).compareTo(this.elements.get(index2)) < 0;
    }

    private void swap(int index1, int index2) {
        // swap with a child having greater value
        E temp = this.elements.get(index1);
        this.elements.set(index1, this.elements.get(index2));
        this.elements.set(index2, temp);
    }

    @Override
    public E peek() {
        if(this.elements.isEmpty())
            throw new IllegalStateException("There are no element to peek");

        return this.elements.get(this.size()-1);
    }

    @Override
    public E poll() {
        if(this.elements.isEmpty())
            throw new IllegalStateException("There are no element to peek");

        return this.elements.remove(this.size()-1);
    }
}
