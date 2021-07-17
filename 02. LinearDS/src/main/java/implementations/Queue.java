package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    //  Take                    Insert
    //  here                     here
    //   _ _ _ _ _ _ _ _ _ _ _ _ _
    //  |_|_|_|_|_|_|_|_|_|_|_|_|_|
    //
    //  head                    tail

    private class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
            next = null;
        }
    }

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> toInsert = new Node<>(element);

        if (isEmpty()) {
            this.head = toInsert;
            this.tail = toInsert;
        }
        else{
            this.tail.next = toInsert;
            this.tail = toInsert;
        }
        this.size++;
    }

    @Override
    public E poll() {
        ensureNotEmpty();

        E value = this.head.element;
        this.head = this.head.next;
        this.size--;
        return value;

//        Node<E> toRemove = this.head;
//        this.head = toRemove.next;
//        this.size--;
//        if (this.size == 0)
//            this.tail = null;
//        return toRemove.element;
    }

    @Override
    public E peek() {
        ensureNotEmpty();
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
            Node<E> current = head;
            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public E next() {
                E value = current.element;
                current = current.next;
                return value;
            }
        };
    }

    private void ensureNotEmpty() {
        if(this.isEmpty())
            throw new IllegalStateException("There is no element (Queue is empty).") ;
    }
}
