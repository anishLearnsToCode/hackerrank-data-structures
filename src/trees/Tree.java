package trees;

import java.util.Scanner;

class Tree<T> {
    private Scanner s = new Scanner(System.in);

    T data;
    Tree left;
    Tree right;

    Tree(){}
    Tree(T data){
        this.data = data;
    }

    public void input(){
        input(this);
    }

    private Tree input(Tree root){
        System.out.print("Enter Data : ");
        int data = s.nextInt();

        if(data != -1)
            root.data = data;
        else return null;

        root.left = input(new Tree());
        root.right = input(new Tree());

        return root;
    }

    public void print(){
        print(this);
    }

    private void print(Tree root){
        if(root == null)
            return;

        System.out.println(root.data + " : " +
                (root.left == null ? "NULL " : root.left.data) + " " + (root.right == null ? "NULL " : root.right.data) );

        print(root.left);
        print(root.right);
    }
}
