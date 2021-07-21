import implementations.Tree;
import implementations.TreeFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] input = {
                "7 19",
                "7 21",
                "7 14",
                "19 1",
                "1 25",
                "19 12",
                "19 31",
                "14 23",
                "23 8",
                "14 6"
        };
        TreeFactory treeFactory = new TreeFactory();
        Tree<Integer> tree = treeFactory.createTreeFromStrings(input);
//        System.out.println(tree.getAsString());
        List<List<Integer>> trees = tree.pathsWithGivenSum(45);
        trees.forEach(System.out::println);
    }
}
//| | | |
//7
//  19
//    1
//      25
//    12
//    31
//  21
//  14
//    23
//      8
//    6