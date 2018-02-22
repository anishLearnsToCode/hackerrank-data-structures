import java.util.LinkedList;
import java.util.Scanner;

public class SwapNodes_Algo {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int n = s.nextInt();
        Tree<Integer> root = prepareTree();
        root.print();

        int testCases = s.nextInt();
        swapNodes(root, testCases);
    }

    private static void swapNodes(Tree<Integer> root, int testCases){
        while (testCases-- > 0){
            int k = s.nextInt();
            swapNodesKTimes(root, k, 1);
            inOrder(root);
        }
    }

    private static void swapNodes(Tree<Integer> root){
        Tree<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    private static void swapNodesKTimes(Tree<Integer> root, int k, int depth){
        if(root == null)
            return;

        if(depth % k == 0)
            swapNodes(root);

        swapNodesKTimes(root.left, k, depth+1);
        swapNodesKTimes(root.right, k, depth+1);
    }

    private static void inOrder(Tree<Integer> root){
        if(root == null){
            return;
        }

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
        System.out.println("");
    }

    private static Tree<Integer> prepareTree(){
        Tree<Integer> root = new Tree<>(1);
        LinkedList<Tree<Integer>> linkedList = new LinkedList<>();
        linkedList.add(root);

        while (!linkedList.isEmpty()){
            Tree<Integer> node = linkedList.pop();
            int left = s.nextInt();
            int right = s.nextInt();

            if(left != -1){
                node.left = new Tree<>(left);
                linkedList.add(node.left);
            }
            if(right != -1){
                node.right = new Tree<>(right);
                linkedList.add(node.right);
            }
        }

        return root;
    }
}
