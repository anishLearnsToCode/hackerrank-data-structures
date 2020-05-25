package trees;

public class PostOrderTraversal {
    public static void main(String[] args) {
        Tree root = new Tree();
        root.input();
        root.print();

        postOrder(root);
    }

    private static void postOrder(Tree root){
        if(root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
}
