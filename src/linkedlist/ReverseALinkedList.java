package linkedlist;

public class ReverseALinkedList {
    static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = null;

        for (SinglyLinkedListNode iter = head ; iter != null ; iter = iter.next) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(iter.data);
            node.next = current;
            current = node;
        }
        return current;
    }
}
