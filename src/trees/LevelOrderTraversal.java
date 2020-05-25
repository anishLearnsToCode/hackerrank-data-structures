package trees;

import java.util.ArrayList;
import java.util.LinkedList;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        Tree root = new Tree();
        root.input();
        root.print();

        ArrayList<Tree> list = levelOrder(root);
        for(int i=0 ; i<list.size() ; i++){
            System.out.print(list.get(i).data + " ");
        }
    }

    private static ArrayList<Tree> levelOrder(Tree root){
        LinkedList<Tree> list = new LinkedList<>();
        ArrayList<Tree> ans = new ArrayList<>();

        list.add(root);

        while (!list.isEmpty()){
            Tree node = list.pop();
            ans.add(node);

            if(node.left != null)
                list.add(node.left);
            if(node.right != null)
                list.add(node.right);
        }

        return ans;
    }
}
