package implementations;

import java.util.*;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String line : input) {
            int[] pair = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            this.addEdge(pair[0],pair[1]);
        }
        return getRoot();
    }

    private Tree<Integer> getRoot() {
        return this.nodesByKeys.values()
                .stream()
                .filter(node -> node.getParent() == null)
                .findFirst()
                .orElse(null);
    }

    public Tree<Integer> createNodeByKey(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<Integer>(key));
        return this.nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        Tree<Integer> parentTree = this.createNodeByKey(parent);
        Tree<Integer> childTree  = this.createNodeByKey(child);

        parentTree.addChild(childTree);
        childTree.setParent(parentTree);
    }

}



