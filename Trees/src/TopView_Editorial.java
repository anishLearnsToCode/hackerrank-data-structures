public class TopView_Editorial {
    public static void main(String[] args) {
        Tree root = new Tree();
        root.input();
        root.print();

        topView(root);
    }

    private static void topView(Tree root){
        goLeft(root.left);
        System.out.print(root.data + " ");
        goRight(root.right);
    }

    private static void goLeft(Tree root){
        if(root == null) return;
        goLeft(root.left);
        System.out.print(root.data + " ");
    }

    private static void goRight(Tree root){
        if(root == null) return;
        goRight(root.right);
        System.out.print(root.data + " ");
    }
}
