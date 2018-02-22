import java.util.ArrayList;
import java.util.Scanner;

public class LowestCommonAncestor {

    public static boolean flag = false;

    public static void main(String[] args) {
        Tree<Integer> root = new Tree<>();
        root.input();
        root.print();

        Scanner s = new Scanner(System.in);
        int data1 = s.nextInt();
        int data2 = s.nextInt();

        Tree<Integer> node = lowestCommonAncestor(root, data1, data2);
        System.out.println("\n" + node.data);
    }

    private static Tree<Integer> lowestCommonAncestor(Tree<Integer> root, int data1, int data2){
        ArrayList<Tree<Integer>> list1 = ancestorOf(root, data1); flag = false;
        ArrayList<Tree<Integer>> list2 = ancestorOf(root, data2); flag = false;

        print(list1);
        print(list2);

        int i;
        for(i=0 ; i<Math.min(list1.size(), list2.size()) ; i++){
            if(list1.get(list1.size() - i - 1).data != list2.get(list2.size() - i - 1).data){
                break;
            }
        }

        return list1.get(list1.size()-i);
    }

    private static ArrayList<Tree<Integer>> ancestorOf(Tree<Integer> root, int data){
        if(root == null){
            return new ArrayList<Tree<Integer>>();
        }

        if((int)root.data == data){
            ArrayList<Tree<Integer>> smallList = new ArrayList<>();
            smallList.add(root);
            flag = true;
            return smallList;
        }

        if(!flag) {
            ArrayList<Tree<Integer>> smallList1 = ancestorOf(root.left, data);
            if (flag) {
                smallList1.add(root);
                return smallList1;
            } else {
                ArrayList<Tree<Integer>> smallList2 = ancestorOf(root.right, data);
                if(flag){
                    smallList2.add(root);
                    return smallList2;
                }
            }
        }

        return new ArrayList<>();
    }

    private static void print(ArrayList<Tree<Integer>> list){
        System.out.println("");
        for(int i=0 ; i<list.size() ; i++){
            System.out.print(list.get(i).data + " ");
        }
    }
}
