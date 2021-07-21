package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree <E>> children;

    public Tree(E key, Tree <E>... children) {
        this.key = key;
        this.parent = null;
        this.children = new ArrayList<>();
        for (Tree<E> child : this.children){
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder output = new StringBuilder();

        getIndentAndKey(output, "", this);

        return output.toString().trim();
    }

    private void getIndentAndKey(StringBuilder output, String indent, Tree<E> eTree) {
        output
                .append(indent)
                .append(eTree.key)
                .append(System.lineSeparator());

        for (Tree<E> child : eTree.children)
            getIndentAndKey(output, indent + "  ", child);

    }

    @Override
    public List<E> getLeafKeys() {
        return getKeysBfs("leaf")
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<E> getMiddleKeys() {
        return getKeysBfs("middle")
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private List<E> getKeysBfs(String type) {
        List<E> keys = new ArrayList<>();
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            if ("middle".equals(type)) {
                if (current.getParent() != null && !current.children.isEmpty())
                    keys.add(current.key);
            }else if ("leaf".equals(type)){
                if (current.children.isEmpty())
                    keys.add(current.key);
            }else if ("all".equals(type)){
                keys.add(current.key);
            }

            current.children.forEach(queue::offer);
        }

        return keys;
    }

    private class Pair {
        private Tree<E> eTree;
        private int level;

        public Pair(Tree<E> eTree) {
            this.eTree = eTree;
            this.level = 0;
        }
        public Pair(Tree<E> eTree, int level) {
            this.eTree = eTree;
            this.level = level;
        }
    }
    @Override
    public Tree<E> getDeepestLeftmostNode() {
        int maxLevel = -1;
        int level = 0;
        Tree<E> DeepestLeft = null;

        ArrayDeque<Pair> queue = new ArrayDeque<>();
        Pair pair = new Pair(this);

        queue.offer(pair);

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            if (current.level > maxLevel){
                DeepestLeft = current.eTree;
                maxLevel = level;
            }
            for (Tree<E> child : current.eTree.children) {
                queue.offer(new Pair(child, ++level));
            }
        }
        return DeepestLeft;

    }

    @Override
    public List<E> getLongestPath() {
        List<E> longestPath = new ArrayList<>();
        Tree<E> current = getDeepestLeftmostNode();

        while (current != null){
            longestPath.add(current.key);
            current = current.parent;
        }

        Collections.reverse(longestPath);
        return longestPath;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> result = new ArrayList<>();

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            checkPath(result, current, current, sum);
            current.children.forEach(queue::offer);
        }

        return result;
    }

    private void checkPath(List<List<E>> result, Tree<E> startTree,Tree<E> currentTree, int currentSum) {
        currentSum -= (int) currentTree.key;

        if (currentSum == 0)
            addPathToResult(result, startTree, currentTree);

        else if (currentSum > 0 && !currentTree.children.isEmpty()) {
            for (Tree<E> child : currentTree.children)
                checkPath(result, startTree, child, currentSum);

        }
    }

    private void addPathToResult(List<List<E>> result, Tree<E> startTree, Tree<E> currentTree) {
        List<E> toAdd = new ArrayList<>();

        while (currentTree != startTree.parent){
            toAdd.add(currentTree.key);
            currentTree = currentTree.parent;
        }
        Collections.reverse(toAdd);
        result.add(toAdd);
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> result =  new ArrayList<>();

        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (!queue.isEmpty()){
            Tree<E> current = queue.poll();

            if (checkSum(current, sum))
                result.add(current);

            current.children.forEach(queue::offer);
        }
        return result;
    }

    private boolean checkSum(Tree<E> eTree, int sum) {
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(eTree);

        while (!queue.isEmpty()){
            Tree<E> current = queue.poll();
            sum -= (Integer) current.key;

            if (sum < 0)
                return false;

            current.children.forEach(queue::offer);
        }
        return sum == 0;
    }
}