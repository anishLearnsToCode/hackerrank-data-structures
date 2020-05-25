package linkedlist;

import java.util.HashMap;

public class FindMergePointOf2Lists {
    public static void main(String[] args) {
        Node head1 = new Node();
        head1.input(1);
        Node head2 = new Node();
        head2.input(1);

        head1.print();
        System.out.println("");
        head2.print();

        int ans = firstCommonNode(head1, head2);
        System.out.println(ans);
    }

    private static int firstCommonNode(Node head1, Node head2){
        HashMap<Node, Integer> map = new HashMap<>();
        Node temp1 = head1;
        while (temp1 != null){
            map.put(temp1, 1);
            temp1 = temp1.next;
        }

        temp1 = head2;
        while (temp1 != null){
            if(map.containsKey(temp1))
                break;

            temp1 = temp1.next;
        }

        return temp1.data;
    }
}
