import java.util.Scanner;

public class SearchSortedLL {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Node linkedList = new Node();
        linkedList.input(1);
        linkedList = linkedList.next;
        int element = in.nextInt();
        int index = search(linkedList, element);
        if(index != -1)
            System.out.println(index);
        else
            System.out.println(element + " doesn't exist in Linked List");
    }

    private static int search(Node linedList, int element){
        Node temp = linedList;
        int index=0;
        while (temp != null && element >= temp.data){
            if(temp.data == element)
                return index;
            temp = temp.next;
            index++;
        }
        return -1;
    }
}
