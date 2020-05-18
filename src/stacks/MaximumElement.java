// https://www.hackerrank.com/challenges/maximum-element/problem

package stacks;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        Stack<Integer> stack = new Stack<>();

        while (queries-- > 0) {
            int type = scanner.nextInt();
            if (type == 1) {
                int element = scanner.nextInt();
                stack.add(element);
                queue.add(element);
            } else if (type == 2) {
                int element = stack.pop();
                queue.remove(element);
            } else {
                System.out.println(queue.peek());
            }
        }
    }
}
