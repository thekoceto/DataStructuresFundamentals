package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        private Node(E element) {
            this.element = element;
        }
    }

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //  Take                    Insert
    //  here                     here
    //   _ _ _ _ _ _ _ _ _ _ _ _ _
    //  |_|_|_|_|_|_|_|_|_|_|_|_|_|
    //
    //  head                    tail

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }

    @Override
    public E poll() {
        ensureNonEmpty();

        E value = this.head.element;
        this.head = this.head.next;
        this.size--;
        return value;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.head.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public E next() {
                E element = this.current.element;
                this.current = this.current.next;
                return element;
            }
        };
    }

    private void ensureNonEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Illegal operation on empty stack");
        }
    }
}
