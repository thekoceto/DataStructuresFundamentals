package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
    private List<E> elements;

    public MinHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.HeapifyUp(this.size()-1);
    }


    private void HeapifyUp(int index) {
        while (index > 0 && isLess(index, parentIndex(index)))
            Collections.swap(this.elements, index, index = parentIndex(index));

    }

    private int parentIndex(int index) {
        return (index-1)/2;
    }


    private boolean isLess(int index1, int index2) {
        return this.elements.get(index1).compareTo(this.elements.get(index2)) < 0;
    }

    private void swap(int index1, int index2) {
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
    private void heapifyDown() {
        int index = 0;
        int leftChildIndex = this.getLeftChildIndex(index);

        while (leftChildIndex < this.elements.size() && isLess(leftChildIndex, index)){
            int rightChildIndex = this.getRightChildIndex(index);
            int child = getLeftChildIndex(index);
            if(rightChildIndex < this.elements.size() && isLess(rightChildIndex, child)){
                child = rightChildIndex;
            }
            if(this.isLess(index, child)){
                break;
            }
            Collections.swap(this.elements, index, child);
            index = child;
            leftChildIndex = this.getLeftChildIndex(index);
        }
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
    @Override
    public E poll() {
        if (this.elements.isEmpty())
            throw new IllegalStateException("There are no element to poll");

        Collections.swap(this.elements , 0 , this.elements.size() -1);
        E toReturn = this.elements.remove(this.elements.size() - 1);
        this.heapifyDown();
        return toReturn;
    }

    @Override
    public void decrease(E element) {
        int elementIndex = this.elements.indexOf(element);
        E heapElement = this.elements.get(elementIndex);
        heapElement.decrease();

        this.HeapifyUp(elementIndex);
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        this.elements.forEach(element -> output.append(element).append(' '));
        return output.toString().trim();
    }
}
