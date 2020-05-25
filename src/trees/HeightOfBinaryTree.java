package trees;

public class HeightOfBinaryTree {
    public static void main(String[] args) {
        Tree root = new Tree();
        root.input();
        root.print();

        int height = heightOfTree(root);
        System.out.println(height);
    }

    private static int heightOfTree(Tree root){
        if(root == null)
            return -1;

        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }
}
