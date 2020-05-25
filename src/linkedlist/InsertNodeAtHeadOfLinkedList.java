package linkedlist;

public class InsertNodeAtHeadOfLinkedList {
    static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data) {
        if (llist == null) {
            llist = new SinglyLinkedListNode(data);
            return llist;
        }

        SinglyLinkedListNode current = new SinglyLinkedListNode(data);
        current.next = llist;
        return current;
    }
}
