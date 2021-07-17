import implementations.Tree;

public class Main {
    public static void main(String[] args) {

        Tree<Integer> tree;
        tree =  new Tree<>(7,
                    new Tree<>(19,
                        new Tree<>(1),
                        new Tree<>(12),
                        new Tree<>(31)),
                    new Tree<>(21),
                    new Tree<>(14,
                        new Tree<>(23),
                        new Tree<>(6))
        );

        System.out.println(tree.orderBfs());
        tree.swap(7,19);
        System.out.println(tree.orderBfs());
        tree.removeNode(19);
        System.out.println(tree.orderBfs());
        tree.removeNode(7);
        System.out.println(tree.orderBfs());

    }
}
