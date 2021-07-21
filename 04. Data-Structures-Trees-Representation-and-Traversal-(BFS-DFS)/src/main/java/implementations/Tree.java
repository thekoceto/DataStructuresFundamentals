package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree <E>> children;

    public Tree(E key, Tree <E>... children) {
        this.key = key;
        this.parent = null;
        this.children = new ArrayList<>();
        for (Tree<E> child : children){
            this.children.add(child);
            child.parent = this;
        }
//        Arrays.stream(children)
//                .forEach(child -> {
//                    this.children.add(child);
//                    child.parent = this;
//                });
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        if (this.key == null)
            return result;

        queue.offer(this);

        while (!queue.isEmpty() ){
            Tree<E> current = queue.poll();
            result.add(current.key);
            current.children.forEach(queue::offer);
        }
        return result;
    }

    @Override
    public List<E> orderDfs() {
        ArrayList<E> result = new ArrayList<>();
        doDfs(this, result);
        return result;
    }
    private void doDfs(Tree<E> current, List<E> result){
        current.children.forEach(child -> doDfs(child,result));
        result.add(current.key);
    }


    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> node = findTreeByKey(parentKey);

        if (node == null)
            throw new IllegalArgumentException();

        node.children.add(child);
        child.parent = node;
    }

    private Tree<E> findTreeByKey(E parentKey) {
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();

            if (current.key == parentKey)
                return current;

            current.children.forEach(queue::offer);
        }
        return null;
    }

	@Override
    public void removeNode(E nodeKey) {
        Tree<E> toRemove = findTreeByKey(nodeKey);

        if (toRemove == null)
            throw new IllegalArgumentException();

        if (toRemove.parent == null) { // this is the root
            toRemove.children = new ArrayList<>();
            toRemove.key = null;
        }else
            toRemove.parent.children.remove(toRemove);
    }

    @Override
    public void swap(E firstKey, E secondKey) {

        Tree<E> firstNode = findTreeByKey(firstKey);
        Tree<E> secondNode = findTreeByKey(secondKey);

        if (firstNode == null || secondNode == null)
            throw new IllegalArgumentException();

        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;

        List<Tree<E>> firstChildren = firstNode.children;
        List<Tree<E>> secondChildren = secondNode.children;

        firstNode.parent  = secondParent;
        secondNode.parent = firstParent;

        firstNode.key  = secondKey;
        secondNode.key = firstKey;

        firstNode.children  = secondChildren;
        secondNode.children = firstChildren;
    }

    private void swapRoot(Tree<E> node) {
        this.key = node.key;
        this.parent = null;
        this.children = node.children;
        node.parent = null;
    }
}