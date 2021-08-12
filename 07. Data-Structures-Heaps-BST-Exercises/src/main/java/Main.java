import interfaces.Heap;
import model.Product;
import solutions.MinHeap;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MinHeap<Product> minHeap = new MinHeap<>();

        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));

        for (Integer element : elements)
            minHeap.add(new Product(element));


        System.out.println(minHeap.toString());

//        BinaryTree bt ;
//        bt = new BinaryTree(7,
//                new BinaryTree(21, null, null),
//                new BinaryTree(14,
//                        new BinaryTree(23, null, null),
//                        new BinaryTree(6,
//                                null,
//                                new BinaryTree(13, null, null))));
//
//
//        System.out.println(bt.inOrderPrint());
//        System.out.println("------------");
//        System.out.println(bt.findLowestCommonAncestor(4, 7));
//        System.out.println("------------");
//        System.out.println(bt.topView());
//
//        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//
//        bst.insert(8);
//        bst.insert(4);
//        bst.insert(2);
//        bst.insert(6);
//        bst.insert(12);
//        bst.insert(10);
//        bst.insert(14);
//
//        System.out.println(bst.inOrderPrint());
//        System.out.println("----------------");
//        System.out.println(" bst contains: " + bst.count() + " element/s");
//        System.out.println("_____________________________________");
//        System.out.println(" i -> contains | ceil | floor | rank |");
//        System.out.println("---------------|------|-------|------|");
//        for (int i = 0; i < 17; i++)
//            System.out.printf("%2d -> %8s | %4d |  %4d | %4d |%n",
//                    i,
//                    bst.contains(i) ? "Yes" : "No",
//                    bst.ceil(i),
//                    bst.floor(i),
//                    bst.rank(i));
//        System.out.println("_______________|______|_______|______|");
    }
}
