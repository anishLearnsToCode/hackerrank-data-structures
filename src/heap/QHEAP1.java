// https://www.hackerrank.com/challenges/qheap1/problem

package heap;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class QHEAP1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        Queue<Integer> minHeap = new PriorityQueue<>();
        while (queries-- > 0) {
            int command = scanner.nextInt();
            if (command == 1) {
                int element = scanner.nextInt();
                minHeap.add(element);
            } else if (command == 2) {
                int element = scanner.nextInt();
                minHeap.remove(element);
            } else {
                System.out.println(minHeap.peek());
            }
        }
    }
}
