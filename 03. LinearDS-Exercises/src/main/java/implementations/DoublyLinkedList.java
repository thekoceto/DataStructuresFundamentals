package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value) {
            this.element = value;
        }
    }

    //       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    //      |_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|
    //   _____________                    _____________
    //  |    first    |        <->       |    last     |
    //  | prev | next |                  | prev | next |

    public DoublyLinkedList() {
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.isEmpty()){
            this.first = newNode;
            this.last = newNode;
        }
        else{
            this.first.prev = newNode;
            newNode.next = this.first;
            this.first = newNode;
        }
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.isEmpty()){
            this.first = newNode;
            this.last = newNode;
        }
        else{
            this.last.next = newNode;
            newNode.prev = this.last;
            this.last = newNode;
        }
        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();

        E value = this.first.element;

        this.first = this.first.next;

        if (this.size == 1)
            this.last = null;
        else
            this.first.prev = null;

        this.size--;
        return value;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();

        E value = this.last.element;

        this.last = this.last.prev;

        if (this.size == 1)
            this.first = null;
        else
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
            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    private void ensureNotEmpty() {
        if(this.isEmpty())
            throw new IllegalStateException("There is no element (DoublyLinkedList is empty).") ;
    }
}
