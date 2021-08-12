package solutions;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
        private final int key;
        private final solutions.BinaryTree first;
        private final solutions.BinaryTree second;

        public BinaryTree(int key, solutions.BinaryTree first, solutions.BinaryTree second) {
            this.key = key;
            this.first = first;
            this.second = second;
        }

    public Integer findLowestCommonAncestor(int first, int second) {
        List<Integer> firstPath = new ArrayList<>();
        List<Integer> secondPath = new ArrayList<>();

        if (!hasPath(this, firstPath,  first) || !hasPath(this, secondPath, second))
            return null;

        for (int i = 0; i < firstPath.size() && i < secondPath.size(); i++) {
            if (!firstPath.get(i).equals(secondPath.get(i)) )
                return firstPath.get(i-1);
        }

        return null;
//        return findLowestCommonAncestor(this, first, second);
    }

    private boolean hasPath(BinaryTree current, List<Integer> path, int destination){
        if (current == null)
            return false;

        path.add(current.key);

        if (current.key == destination)
            return true;

        if (hasPath(current.first, path, destination) || hasPath(current.second, path, destination))
            return true;

        path.remove(path.size()-1);

        return false;
    }

    private Integer findLowestCommonAncestor(BinaryTree current, int first, int second) {
        if (current == null)
            return null;

        if (current.key >= first && current.key >= second)
            return findLowestCommonAncestor(current.first, first, second);

        if (current.key <= first && current.key <= second)
            return findLowestCommonAncestor(current.second, first, second);

        return current.key;
    }

    public List<Integer> topView() {
        List<Integer> result = new ArrayList<>();

        result.add(this.key);
        travelOnEdge(result, this.first, "first");
        travelOnEdge(result, this.second, "second");

        return result;
    }

    private void travelOnEdge(List<Integer> result, BinaryTree current, String edge) {
        if (current == null)
            return;

        result.add(current.key);

        if ("first".equals(edge))
            travelOnEdge(result, current.first, edge);

        if ("second".equals(edge))
            travelOnEdge(result, current.second, edge);
    }

    // ------------------
    public String inOrderPrint() {
        StringBuilder output = new StringBuilder();
        inOrderPrint(output, "", this);
        return output.toString();
    }

    private void inOrderPrint(StringBuilder output, String indent, BinaryTree current) {

        if (current.first != null)
            this.inOrderPrint(output, indent + "  ", current.first);

        output
                .append(indent)
                .append(current.key)
                .append(System.lineSeparator());

        if (current.second != null)
            this.inOrderPrint(output, indent + "  ", current.second);

    }
}
