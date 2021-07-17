package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> top;
    private int size;

    private class Node<E> {
        private E element;
        private Node<E> next;

        public Node (E value){
            this.element = value;
        }
    }
    public Stack(){
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> toPush = new Node<>(element);
        toPush.next = this.top;
        this.top = toPush;
        this.size++;
    }

    @Override
    public E pop() {
        notEmpty();
        Node<E> toPop = this.top;
        this.top = this.top.next;
        this.size--;
        return toPop.element;
    }

    @Override
    public E peek() {
        notEmpty();
        return this.top.element;
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
            Node<E> current = top;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    private void notEmpty() {
        if (this.size == 0)
            throw new IllegalStateException();
    }


}
