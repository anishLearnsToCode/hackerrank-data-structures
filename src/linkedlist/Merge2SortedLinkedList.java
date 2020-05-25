package linkedlist;

import java.util.Scanner;

public class Merge2SortedLinkedList {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Node head1 = new Node(), head2 = new Node();
        head1.input(1);
        head2.input(1);

        head1.print();
        System.out.println("");
        head2.print();

        Node head = merge(head1, head2);
        System.out.println("");
        head.print();
    }

    private static Node merge(Node head1, Node head2){
        Node ans = new Node(2);
        Node temp1, temp2, ansTemp;
        for(temp1 = head1, temp2 = head2, ansTemp = ans ; temp1 != null && temp2 != null ; ){
            if(temp1.data <= temp2.data){
                ansTemp.next = new Node(temp1.data);
                ansTemp = ansTemp.next;
                temp1 = temp1.next;
            } else {
                ansTemp.next = new Node(temp2.data);
                ansTemp = ansTemp.next;
                temp2 = temp2.next;
            }
        }

        if(temp1 == null){
            while (temp2 != null){
                ansTemp.next = new Node(temp2.data);
                ansTemp = ansTemp.next;
                temp2 = temp2.next;
            }
        } else {
            while (temp1 != null){
                ansTemp.next = new Node(temp1.data);
                ansTemp = ansTemp.next;
                temp1 = temp1.next;
            }
        }

        return ans.next;
    }
}
