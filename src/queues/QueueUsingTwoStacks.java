package queues;

import java.util.LinkedList;
import java.util.Scanner;

public class QueueUsingTwoStacks {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int queries = in.nextInt();
        performQueries(queries);
    }

    private static void performQueries(int queries){
        LinkedList<Integer> linkedList = new LinkedList<>();

        while (queries-- > 0){
            int type = in.nextInt();
            if(type == 1){
                int data = in.nextInt();
                linkedList.add(data);
            } else if(type == 2){
                linkedList.pop();
            } else {
                System.out.println(linkedList.peek());
            }
        }
    }
}
