package trees;

public class InorderTraversal {
    public static void main(String[] args) {
        Tree root = new Tree();
        root.input();
        root.print();

        inOrder(root);
    }

    private static void inOrder(Tree root){
        if(root == null)
            return;

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
}
