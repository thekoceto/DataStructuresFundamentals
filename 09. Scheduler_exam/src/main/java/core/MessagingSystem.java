package core;

import model.Message;
import shared.DataTransferSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MessagingSystem implements DataTransferSystem {
    private Node root;
    int size;

    private class Node{
        private Message message;
        private Node left;
        private Node right;

        public Node(Message message) {
            this.message = message;
        }
    }

    public MessagingSystem() {
        this.size = 0;
    }

    @Override
    public void add(Message message) {

        this.root = add(this.root, message);

        this.size++;
    }

    private Node add(Node current, Message message) {
        if (current == null)
            current = new Node(message);
        else
            if (current.message.getWeight() == message.getWeight())
                throw new IllegalArgumentException();

        if (message.getWeight() < current.message.getWeight())
            current.left = add(current.left, message);

        if (message.getWeight() > current.message.getWeight())
            current.right = add(current.right, message);

        return current;
    }


    @Override
    public Message getByWeight(int weight) {
        return getByWeight(this.root, weight);
    }

    private Message getByWeight(Node current, int weight) {
        if (current.message.getWeight() == weight)
            return current.message;

        if (weight < current.message.getWeight())
            getByWeight(current.left, weight);

        if (weight > current.message.getWeight())
            getByWeight(current.right, weight);

        throw new IllegalArgumentException();
    }

    @Override
    public Message getLightest() {
        if (this.size == 0)
            throw new IllegalArgumentException();

        Node current = this.root;

        while (current.left != null)
            current = current.left;

        return current.message;
    }

    @Override
    public Message getHeaviest() {
        if (this.size == 0)
            throw new IllegalArgumentException();

        Node current = this.root;
        while (current.right != null)
            current = current.right;

        return current.message;
    }

    @Override
    public Message deleteLightest() {
        if (this.size == 0)
            throw new IllegalArgumentException();

        Message lightest;
        if (this.root.left == null){
            lightest = this.root.message;
            this.root = this.root.right;
        }
        else {
            Node current = this.root;

            while (current.left.left != null)
                current = current.left;

            lightest = current.left.message;
            current.left = current.left.right;
        }
        this.size--;
        return lightest;
    }

    @Override
    public Message deleteHeaviest() {
        if (this.size == 0)
            throw new IllegalArgumentException();

        Message heaviest;
        if (this.root.right == null) {
            heaviest = this.root.message;
            this.root = this.root.left;
        }
        else {
            Node current = this.root;

            while (current.right.right != null)
                current = current.right;

            heaviest = current.right.message;
            current.right = current.right.left;
        }
        this.size--;
        return heaviest;
    }

    @Override
    public Boolean contains(Message message) {
        return contains(this.root , message) != null;
    }

    private Node contains(Node current, Message message) {
        if (current == null || current.message.getWeight() == message.getWeight())
            return current;

        if (message.getWeight() < current.message.getWeight())
            return contains(current.left, message);

        return contains(current.right, message);
    }

    @Override
    public List<Message> getOrderedByWeight() {
        return getInOrder();
    }


    @Override
    public List<Message> getPostOrder() {
        List<Message> result = new ArrayList<>();
        recursiveOrder(result ,this.root, "postOrder");
        return result;
    }

    @Override
    public List<Message> getPreOrder() {
        List<Message> result = new ArrayList<>();
        recursiveOrder(result ,this.root, "preOrder");
        return result;
    }

    @Override
    public List<Message> getInOrder() {
        List<Message> result = new ArrayList<>();
        recursiveOrder(result ,this.root, "inOrder");
        return result;
    }

    private void recursiveOrder(List<Message> result, Node current, String type) {
        if ("preOrder".equals(type))
            result.add(current.message);

        if (current.left != null)
            recursiveOrder(result, current.left, type);

        if ("inOrder".equals(type))
            result.add(current.message);

        if (current.right != null)
            recursiveOrder(result, current.right, type);

        if ("postOrder".equals(type))
            result.add(current.message);
    }

    @Override
    public int size() {
        return this.size;
    }

    // ------------------
    public String inOrderPrint() {
        StringBuilder output = new StringBuilder();
        if (this.root != null)
            inOrderPrint(output, "", this.root);
        return output.toString();
    }

    private void inOrderPrint(StringBuilder output, String indent, MessagingSystem.Node current) {

        if (current.right != null)
            this.inOrderPrint(output, indent + "  ", current.right);

        output
                .append(indent)
                .append(current.message.getWeight())
                .append(System.lineSeparator());

        if (current.left != null)
            this.inOrderPrint(output, indent + "  ", current.left);

    }
}
