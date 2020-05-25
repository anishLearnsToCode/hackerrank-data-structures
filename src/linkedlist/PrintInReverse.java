package linkedlist;

import java.util.Scanner;

public class PrintInReverse {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Node head = new Node();
        head.input(1);
        head.print();

        head = reverseLL(head);
        System.out.println("");
        head.print();
    }

    private static Node reverseLL(Node head){
        if(head.size() <= 1)
            return head;
        else if (head.size() == 2) {
            Node temp = head.next;
            temp.next = head;
            head.next = null;
            return temp;
        }

        Node first, second, third;
        for(first = head, second = first.next, third = second.next, first.next = null ; third != null ; first = second, second = third, third = third.next){
            second.next = first;
        }
        second.next = first;
        return second;
    }
}