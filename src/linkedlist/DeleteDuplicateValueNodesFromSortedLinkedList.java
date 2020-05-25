package linkedlist;

public class DeleteDuplicateValueNodesFromSortedLinkedList {
    public static void main(String[] args) {
        Node head = new Node();
        head.input(1);
        head.print();

        removeDuplicateNodes(head);
        System.out.println(); head.print();
    }

    private static void removeDuplicateNodes(Node head){

        Node temp = head.next, first = head;
        int nodeData = head.data;

        while (temp != null){
            if(temp.data == nodeData){
                first.next = temp.next;
                temp = temp.next;
            } else {
                nodeData = temp.data;
                first = first.next;
                temp = temp.next;
            }
        }
    }
}
