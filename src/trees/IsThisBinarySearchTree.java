package trees;

public class IsThisBinarySearchTree {
    public static void main(String[] args) {
        CustomTree<Integer> root = new CustomTree<>();
        root.input();
        root.print();

        boolean ans = isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(ans);
    }

    private static boolean isBST(CustomTree<Integer> root, int minValue, int maxValue){
        if(root == null)
            return true;

        boolean left, right;

        if(root.left != null) left = (root.left.data < root.data) && (root.left.data > minValue);
        else left = true;

        if(root.right != null) right = (root.right.data > root.data) && (root.right.data < maxValue);
        else right = true;

        return left && right && isBST(root.left, minValue, root.data) && isBST(root.right, root.data, maxValue);
    }
}
