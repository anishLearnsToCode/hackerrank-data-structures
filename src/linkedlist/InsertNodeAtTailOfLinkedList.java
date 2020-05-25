package linkedlist;

public class InsertNodeAtTailOfLinkedList {
    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
        if (head == null) {
            head = new SinglyLinkedListNode(data);
            return head;
        }

        SinglyLinkedListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new SinglyLinkedListNode(data);

        return head;
    }
}
