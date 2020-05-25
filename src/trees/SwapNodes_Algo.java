package trees;

import java.util.LinkedList;
import java.util.Scanner;

public class SwapNodes_Algo {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int n = s.nextInt();
        CustomTree<Integer> root = prepareTree();
        root.print();

        int testCases = s.nextInt();
        swapNodes(root, testCases);
    }

    private static void swapNodes(CustomTree<Integer> root, int testCases){
        while (testCases-- > 0){
            int k = s.nextInt();
            swapNodesKTimes(root, k, 1);
            inOrder(root);
        }
    }

    private static void swapNodes(CustomTree<Integer> root){
        CustomTree<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    private static void swapNodesKTimes(CustomTree<Integer> root, int k, int depth){
        if(root == null)
            return;

        if(depth % k == 0)
            swapNodes(root);

        swapNodesKTimes(root.left, k, depth+1);
        swapNodesKTimes(root.right, k, depth+1);
    }

    private static void inOrder(CustomTree<Integer> root){
        if(root == null){
            return;
        }

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
        System.out.println("");
    }

    private static CustomTree<Integer> prepareTree(){
        CustomTree<Integer> root = new CustomTree<>(1);
        LinkedList<CustomTree<Integer>> linkedList = new LinkedList<>();
        linkedList.add(root);

        while (!linkedList.isEmpty()){
            CustomTree<Integer> node = linkedList.pop();
            int left = s.nextInt();
            int right = s.nextInt();

            if(left != -1){
                node.left = new CustomTree<>(left);
                linkedList.add(node.left);
            }
            if(right != -1){
                node.right = new CustomTree<>(right);
                linkedList.add(node.right);
            }
        }

        return root;
    }
}
