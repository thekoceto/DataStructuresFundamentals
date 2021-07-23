package implementations;

import interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    @Override
    public void insert(E element) {
        Node<E> current = this.root;
        Node<E> previous = current;
        int compare = 0;

        while (current != null) {
            previous = current;
            compare = current.value.compareTo(element);
            if (compare > 0)
                current = current.leftChild;
            else if (compare < 0)
                current = current.rightChild;
            else
                return;
        }
        if (compare > 0)
            previous.leftChild = new Node<>(element);
        else if (compare < 0)
            previous.rightChild = new Node<>(element);
        else
            this.root = new Node<>(element);
    }

    @Override
    public boolean contains(E element) {
        Node<E> current = this.root;

        while (current != null) {
            int compare = current.value.compareTo(element);
            if (compare > 0)
                current = current.leftChild;
            else if (compare < 0)
                current = current.rightChild;
            else
                return true;
        }
        return false;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        BinarySearchTree<E> newTree = new BinarySearchTree<>();
        Node<E> current = this.root;

        while (current != null) {
            int compare = current.value.compareTo(element);
            if (compare > 0)
                current = current.leftChild;
            else if (compare < 0)
                current = current.rightChild;
            else {
                copyTree(newTree, current);
                return newTree;
            }
        }
        return newTree;
    }

    private void copyTree(BinarySearchTree<E> newTree, Node<E> current) {
        if(current == null)
            return;

        newTree.insert(current.value);
        this.copyTree(newTree, current.leftChild);
        this.copyTree(newTree, current.rightChild);
    }
    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.root.leftChild;
    }

    @Override
    public Node<E> getRight() {
        return this.root.rightChild;
    }

    @Override
    public E getValue() {
        return this.root.value;
    }
}
