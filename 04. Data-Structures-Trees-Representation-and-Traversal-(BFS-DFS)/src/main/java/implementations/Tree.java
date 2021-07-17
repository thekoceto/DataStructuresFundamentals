package implementations;

import interfaces.AbstractTree;

import java.util.*;


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
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (!queue.isEmpty()){
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
        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();

            if (current.key == parentKey)
                return current;

            current.children.forEach(queue::offer);
        }
        return null;
    }

//    private Tree<E> findTreeByKey(E parentKey) {
//        Deque<Tree<E>> stack = new ArrayDeque<>();
//        stack.push(this);
//
//        while (!stack.isEmpty()){
//            Tree<E> current = stack.pop();
//
//            if (current.key == parentKey)
//                return current;
//
//            current.children.forEach(stack::push);
//        }
//        return null;
//    }

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

        Tree<E> node1 = findTreeByKey(firstKey);
        Tree<E> node2 = findTreeByKey(secondKey);

        if (node1 == null || node2 == null)
            throw new IllegalArgumentException();

        node1.key = secondKey;
        node2.key = firstKey;

        Tree<E> node1Parent = node1.parent;
        Tree<E> node2Parent = node2.parent;

        node1.parent = node2Parent;
        node2.parent = node1Parent;

//        tree =  new Tree<>(7,
//                new Tree<>(19,
//                        new Tree<>(1),
//                        new Tree<>(12),
//                        new Tree<>(31)),
//                new Tree<>(21),
//                new Tree<>(14,
//                        new Tree<>(23),
//                        new Tree<>(6))
//        );
        if (node1Parent == null) {
            swapRoot(node1);
            return;
        } else if (node2Parent == null) {
            swapRoot(node2);
            return;
        }


        int index1 = node1Parent.children.indexOf(node1);
        int index2 = node2Parent.children.indexOf(node2);

        node1Parent.children.set(index1, node2);
        node2Parent.children.set(index2, node2);
    }

    private void swapRoot(Tree<E> node) {
        this.key = node.key;
        this.parent = null;
        this.children = node.children;
        node.parent = null;
    }
}