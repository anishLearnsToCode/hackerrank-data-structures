package linkedlist;

import java.util.Scanner;

public class InsertANodeInSortedDoublyLinkedList {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        DoublyLL head = new DoublyLL();
        head.input();
        head.print();

        int data = s.nextInt();
        head = insertNode(head, data);
        System.out.println();
        head.print();
    }

    private static DoublyLL insertNode(DoublyLL head, int data){
        if(head == null)
            return head;

        if(data <= head.data){
            DoublyLL node = new DoublyLL(data);
            node.next = head;
            head.prev = node;
            return node;
        }

        DoublyLL temp = head.next, first = head;
        while (temp != null){
            if(data <= temp.data){
                DoublyLL node = new DoublyLL(data);
                node.next = temp;
                temp.prev = node;
                node.prev = first;
                first.next = node;
                break;
            }

            first = first.next;
            temp = temp.next;
        }

        if(temp == null){
            DoublyLL node = new DoublyLL(data);
            node.prev = first;
            first.next = node;
        }

        return head;
    }
}
