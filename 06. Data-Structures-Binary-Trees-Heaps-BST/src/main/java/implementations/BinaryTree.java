package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree(E key, BinaryTree<E> left, BinaryTree<E> right) {
        this.setKey(key);
        this.left = left;
        this.right = right;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder output = new StringBuilder();

        getIndentAndKey(output, "", this);

        return output.toString().trim();
    }
//
//    private void getIndentAndKey(StringBuilder output, String indent) {
//        output
//                .append(indent)
//                .append(this.key)
//                .append(System.lineSeparator());
//
//        if (this.left != null)
//            this.left.getIndentAndKey(output, indent + "  ");
//
//        if (this.right != null)
//            this.right.getIndentAndKey(output, indent + "  ");
//    }

    private void getIndentAndKey(StringBuilder output, String indent, BinaryTree<E> current) {
        output
                .append(indent)
                .append(current.key)
                .append(System.lineSeparator());

        if (current.left != null)
            this.getIndentAndKey(output, indent + "  ", current.left);

        if (current.right != null)
            this.getIndentAndKey(output, indent + "  ", current.right);
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();

        recursiveOrder(list, this, "preOrder");

        return list;
    }


    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();

        recursiveOrder(list, this, "inOrder");

        return list;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();

        recursiveOrder(list, this, "postOrder");

        return list;
    }

    private void recursiveOrder(List<AbstractBinaryTree<E>> list, BinaryTree<E> current, String type) {
        if ("preOrder".equals(type))
            list.add(current);

        if (current.left != null)
            recursiveOrder(list, current.left, type);

        if ("inOrder".equals(type))
            list.add(current);

        if (current.right != null)
            recursiveOrder(list, current.right, type);

        if ("postOrder".equals(type))
            list.add(current);
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (this.left != null)
            this.left.forEachInOrder(consumer);
        consumer.accept(this.key);
        if (this.right != null)
            this.right.forEachInOrder(consumer);
    }
}
