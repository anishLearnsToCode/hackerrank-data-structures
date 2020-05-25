package linkedlist;

public class PrintElementsOfLinkedList {
    static void printLinkedList(SinglyLinkedListNode head) {
        if (head != null) {
            System.out.println(head.data);
            printLinkedList(head.next);
        }
    }
}
