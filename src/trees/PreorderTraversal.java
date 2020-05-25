package trees;

public class PreorderTraversal {
    public static void main(String[] args) {
        Tree root = new Tree();
        root.input();
        root.print();

        preOrder(root);
    }

    private static void preOrder(Tree root){
        if(root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
}
