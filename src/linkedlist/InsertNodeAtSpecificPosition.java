package linkedlist;

public class InsertNodeAtSpecificPosition {
    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
        if (position == 0) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(data);
            node.next = head;
            return node;
        }

        SinglyLinkedListNode current = head;
        while (position-- > 1) {
            current = current.next;
        }
        SinglyLinkedListNode node = new SinglyLinkedListNode(data);
        node.next = current.next;
        current.next = node;
        return head;
    }
}
