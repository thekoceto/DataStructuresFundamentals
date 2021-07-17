package implementations;

import interfaces.SimpleCollection;

import java.util.Iterator;

public class ReversedList<E> implements SimpleCollection<E> {
    private static final int INITIAL_CAPACITY = 2;

    private Object[] elements;
    private int capacity;
    private int size;

    public ReversedList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    @Override
    public void add(E element) {
        if (this.size == this.capacity)
            grow();

        this.elements[this.size++] = element;
    }

    private void grow() {
        Object[] newElements = new Object[this.capacity *= 2];

        for (int i = 0; i < this.size; i++)
            newElements[i] = this.elements[i];

        this.elements = newElements;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public E get(int index) {
        return this.getAtRealIndex(this.size - 1 - index);
    }

    //   3   2   1   0 <- index
    //   0   1   2   3 <- realIndex
    // [10][11][12][13]

    @Override
    public E removeAt(int index) {

        E toRemove = this.getAtRealIndex(index);

        for (int i = index; i < this.size-1; i++)
            this.elements[i] = this.elements[i+1];

        this.elements[--this.size] = null;

        return toRemove;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException("The index " + (this.size - 1 - index) + " is out of bound.");
    }

    @SuppressWarnings("unchecked")
    private E getAtRealIndex(int index){
        this.checkIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int iterIndex = size - 1;
            @Override
            public boolean hasNext() {
                return iterIndex >= 0;
            }

            @Override
            public E next() {
                return getAtRealIndex(iterIndex--);
            }
        };
    }
}