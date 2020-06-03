// https://www.hackerrank.com/challenges/jesse-and-cookies/problem

package heap;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class JesseAndCookies {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int threshold = scanner.nextInt();
        Queue<Long> minHeap = new PriorityQueue<>();
        while (length-- > 0) {
            minHeap.add(scanner.nextLong());
        }
        System.out.println(minimumOperations(minHeap, threshold));
    }

    private static int minimumOperations(Queue<Long> minHeap, int threshold) {
        int operations = 0;
        while (minHeap.peek() < threshold && minHeap.size() >= 2) {
            long cookie = minHeap.poll() + 2 * minHeap.poll();
            minHeap.add(cookie);
            operations++;
        }

        return minHeap.peek() >= threshold ? operations : -1 ;
    }
}
