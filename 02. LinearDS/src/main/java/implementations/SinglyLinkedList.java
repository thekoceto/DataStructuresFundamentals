package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    private class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }

    public SinglyLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    //   _ _ _ _ _ _ _ _ _ _ _ _ _
    //  |_|_|_|_|_|_|_|_|_|_|_|_|_|
    //
    //  first      <->          last

    @Override
    public void addFirst(E element) {
        Node<E> toAdd = new Node<>(element);
        if (this.isEmpty()){
            this.first = this.last = toAdd;
        }
        else{
            this.first.prev = toAdd;
            toAdd.next = this.first;
            this.first = toAdd;
        }
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> toAdd = new Node<>(element);

        if (this.isEmpty()){
            this.first = toAdd;
            this.last = toAdd;
        }
        else{
            this.last.next = toAdd;
            toAdd.prev = this.last;
            this.last = toAdd;
        }
        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();

        E value = this.first.element;

        this.first = this.first.next;
        this.first.prev = null;
        this.size--;
        return value;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();

        E value = this.last.element;

        this.last = this.last.prev;
        this.last.next = null;
        this.size--;
        return value;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.first.element;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        return this.last.element;
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
            Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current.next != null;
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
            throw new IllegalStateException("There is no element (LinkedList is empty).") ;
    }
}
