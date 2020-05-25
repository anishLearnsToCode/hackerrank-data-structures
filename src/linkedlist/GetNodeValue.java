package linkedlist;

import java.util.Scanner;

public class GetNodeValue {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Node head = new Node();
        head.input(1);
        int position = s.nextInt();
        Node ans = nodeFromTail(head, position);
        System.out.println(ans.data);
    }

    private static Node nodeFromTail(Node head, int position){
        int length = head.size();
        Node temp = head;
        for(int i=0 ; i<length-position-1 ; i++, temp = temp.next);
        return temp;
    }
}
