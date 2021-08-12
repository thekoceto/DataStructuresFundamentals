import java.util.ArrayList;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

//    public BinarySearchTree() {
//    }
//
//    public BinarySearchTree(Node<E> root) {
//        this.root = root;
//    }

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }

    public void eachInOrder(Consumer<E> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<E> current, Consumer<E> consumer) {
        if (current == null)
            return;
        this.eachInOrder(current.leftChild, consumer);
        consumer.accept(current.value);
        this.eachInOrder(current.rightChild, consumer);
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        this.root = this.insert(this.root, element);
    }

    private Node<E> insert(Node<E> current, E element) {
        if (current == null)
            return new Node(element);

        if (element.compareTo(current.value) < 0)
            current.leftChild = insert(current.leftChild, element);

        else if (element.compareTo(current.value) > 0)
            current.rightChild = insert(current.rightChild, element);

        return current;
    }

    public boolean contains(E element) {
        return contains(this.root, element) != null;
    }

    private Node<E> contains(Node<E> current, E element) {

//        if (current == null || current.value.equals(element))
//            return current;
//
//        if (current.value.compareTo(element) > 0)
//            return contains(current.getLeft(), element);
//
//        return contains(current.getRight(), element);

        if(current == null || current.value.equals(element))
            return current;

        Node<E> left = contains(current.leftChild, element);
        Node<E> right = contains(current.rightChild, element);

        return left != null  ? left : right;

    }

    public BinarySearchTree<E> search(E element) {
        BinarySearchTree<E> newTree = new BinarySearchTree<E>();

        // find element and copy BST
        copy(newTree, contains(this.root, element));

        return newTree;
    }

    private void copy(BinarySearchTree<E> newTree, Node<E> current) {
        if(current == null)
            return;

        newTree.insert(current.value);
        copy(newTree, current.getLeft());
        copy(newTree, current.getRight());
    }

    public List<E> range(E first, E second) {
        List<E> elements = new ArrayList<>();
        getElementInRange(elements, this.root, first, second);
        return elements;
    }

    private void getElementInRange(List<E> elements, Node<E> current, E first, E second) {
        if(current == null)
            return;

        if (isIncludeInRange(current, first, second))
            elements.add(current.value);

        getElementInRange(elements, current.getLeft(),  first, second);
        getElementInRange(elements, current.getRight(), first, second);
    }

    private boolean isIncludeInRange(Node<E> current, E first, E second) {
        return current.value.compareTo(first) >= 0 && current.value.compareTo(second) <= 0;
    }

    public void deleteMin() {
        ensureNotEmpty();

        if (this.root.leftChild == null)
            this.root = this.root.rightChild;
        else {
            Node<E> current = this.root;
            while (current.leftChild.leftChild != null)
                current = current.leftChild;

            current.leftChild = current.leftChild.rightChild;
        }
    }


    public void deleteMax() {
        ensureNotEmpty();

        if (this.root.rightChild == null)
            this.root = this.root.leftChild;
        else {
            Node<E> current = this.root;
            while (current.rightChild.rightChild != null)
                current = current.rightChild;

            current.rightChild = current.rightChild.leftChild;
        }
    }

    public int count() {
        return this.count(this.root);
    }

    private int count(Node<E> current) {
        return current == null ? 0 : 1
                + count(current.leftChild)
                + count(current.rightChild);
    }

    public int rank(E element) {
        return rank(this.root, element);
    }

    private int rank(Node<E> current, E element) {
        return current == null ? 0 :
                (current.value.compareTo(element) < 0 ? 1 : 0)
                        + rank(current.leftChild, element)
                        + rank(current.rightChild, element);
    }

    public E ceil(E element) {
        return ceil(this.root, null, element);
    }

    private E ceil(Node<E> current, E larger, E element) {
        if (current == null)
            return larger;

        if (current.value.compareTo(element) > 0)
            return ceil(current.leftChild, current.value, element);

        return ceil(current.rightChild, larger, element);

    }

    public E floor(E element) {
        return floor(this.root, null, element);
    }

    private E floor(Node<E> current, E smaller, E element) {
        if (current == null)
            return smaller;

        if (current.value.compareTo(element) >= 0)
            return floor(current.leftChild, smaller, element);

        return floor(current.rightChild, current.value, element);
    }

    private void ensureNotEmpty() {
        if (this.root == null)
            throw new IllegalArgumentException("Can't delete element in empty tree.");
    }

    // ------------------
    public String inOrderPrint() {
        StringBuilder output = new StringBuilder();
        if (this.root != null)
            inOrderPrint(output, "", this.root);
        return output.toString();
    }

    private void inOrderPrint(StringBuilder output, String indent, Node<E> current) {

        if (current.rightChild != null)
            this.inOrderPrint(output, indent + "  ", current.rightChild);

        output
                .append(indent)
                .append(current.value)
                .append(System.lineSeparator());

        if (current.leftChild != null)
            this.inOrderPrint(output, indent + "  ", current.leftChild);

    }
}