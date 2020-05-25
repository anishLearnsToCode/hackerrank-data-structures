package stacks;

import java.util.Scanner;
import java.util.Stack;

public class SimpleTextEditor {
    private static Scanner in = new Scanner(System.in);

    public static void main(String args[]){
        int queries = in.nextInt();
        performQueries(queries);
    }

    private static void performQueries(int queries){
        Stack<String> stack = new Stack<>();
        stack.push("");

        while (queries-- > 0){
            int queryType = in.nextInt();

            if(queryType == 1){
                String string = in.next();
                stack.push(stack.peek() + string);
            } else if(queryType == 2){
                int k = in.nextInt();
                stack.push(stack.peek().substring(0, stack.peek().length()-k));
            } else if(queryType == 3){
                int k = in.nextInt();
                System.out.println(stack.peek().charAt(k-1));
            } else {
                stack.pop();
            }
        }
    }
}
