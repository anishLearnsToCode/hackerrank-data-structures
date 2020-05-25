package trees;

import java.util.Scanner;

public class BST_Insertion {

    public static void main(String[] args) {
        Tree<Integer> root = new Tree<>();
        root.input();
        root.print();

        Scanner s = new Scanner(System.in);
        int data = s.nextInt();

        root = insert(root, data);
        System.out.println();
        root.print();
    }

    private static Tree insert(Tree<Integer> root, int data){
        if(root == null){
            Tree<Integer> obj = new Tree<>(data);
            return obj;
        }

        if(data < root.data){
            if(root.left != null) insert(root.left, data);
            else {
                root.left = new Tree<>(data);
                return root;
            }
        } else {
            if(root.right != null) insert(root.right, data);
            else {
                root.right = new Tree<>(data);
                return root;
            }
        }

        return root;
    }
}
