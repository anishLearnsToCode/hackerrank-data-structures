package linkedlist;

import java.util.HashMap;

public class CycleDetection {
    public static void main(String[] args) {
        Node head = new Node();
        head.input(1);

        boolean ans = containsCycle(head);
        System.out.println(ans);
    }

    private static boolean containsCycle(Node head){
        HashMap<Node, Integer> map = new HashMap<>();
        Node temp = head;

        while (temp != null){
            if(map.containsKey(temp))
                return true;
            else map.put(temp, 1);

            temp = temp.next;
        }

        return false;
    }
}
