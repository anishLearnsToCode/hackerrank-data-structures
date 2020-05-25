package linkedlist;

public class ReverseDoublyLinkedList {
    public static void main(String[] args) {
        DoublyLL head = new DoublyLL();
        head.input();
        head.print();

        head = reverse(head);
        System.out.println("");
        head.print();
    }

    private static DoublyLL reverse(DoublyLL head){
        if(size(head) <= 1){
            return head;
        }

        DoublyLL first = head, temp = head.next;
        while (temp != null){
            first.next = (first.prev == null ? null : first.prev) ;
            first.prev = temp;

            temp = temp.next;
            first = first.prev;
        }

        first.next = first.prev;
        first.prev = null;

        return first;
    }

    private static int size(DoublyLL head){
        int count=0;
        DoublyLL temp = head;

        while (temp != null){
            count++;
            temp = temp.next;
        }

        return count;
    }
}
