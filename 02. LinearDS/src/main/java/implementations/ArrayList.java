package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private Object[] elements;
    private int head;

    private int size;
    private int capacity;

    public ArrayList(){
        this.elements = new Object[4];
        this.size = 0;
        this.capacity = 4;
    }

    @Override
    public boolean add(E element) {
        if (this.size == this.capacity)
            resize();

        this.elements[this.size++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (isIndexNotValid(index))
            return false;

        if (this.size == this.capacity)
            resize();

        for (int i = this.size - 1; i >= index ; i--)
            this.elements[i+1] = this.elements[i];

        this.elements[index] = element;
        this.size++;
        return true;
    }

    @Override
    public E get(int index) {
        ensureIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        ensureIndex(index);
        Object current = this.elements[index];

        this.elements[index] = element;

        return (E) current;
    }

    @Override
    public E remove(int index) {
        ensureIndex(index);
        Object current = this.elements[index];

        for (int i = index; i < this.size; i++)
            elements[i] = elements[i+1];

        this.size--;

        return (E) current;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (element.equals(this.elements[i]))
                return i;
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return this.indexOf(element) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private boolean isIndexNotValid(int index) {
        return index < 0 || index >= this.size;
    }

    private void resize() {
        this.capacity *= 2;
        Object[] newElements = new Object[this.capacity];

        for (int i = 0; i < this.size; i++)
            newElements[i] = this.elements[i];

        this.elements = newElements;
    }

    private void ensureIndex(int index) {
        if (isIndexNotValid(index))
            throw new IndexOutOfBoundsException(
                    "Can not use index " + index + " on ArrayList with " + this.size + "elements.");
    }

}
