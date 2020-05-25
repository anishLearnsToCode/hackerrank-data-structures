package linkedlist;

import java.util.Scanner;

public class Compare2LinkedList {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Node head1 = new Node();
        head1.input(1);
        Node head2 = new Node();
        head2.input(1);

        head1.print();
        head2.print();

        int ans = compare(head1, head2);
        System.out.println("\n" + ans);
    }

    private static int compare(Node head1, Node head2){
        Node temp1 = head1, temp2 = head2;

        if(head1.size() != head1.size())
            return 0;

        while (temp1 != null && temp2 != null){
            if(temp1.data != temp2.data)
                return 0;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return 1;
    }
}
