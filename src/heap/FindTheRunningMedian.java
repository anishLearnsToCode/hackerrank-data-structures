package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FindTheRunningMedian {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        int[] array = getArray(length);
        printMedians(array);
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
        return array;
    }

    private static void printMedians(int[] array) {
        Queue<Integer> lowerHalf = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> upperHalf = new PriorityQueue<>();
        for (int number : array) {
            addToHeap(lowerHalf, upperHalf, number);
            redolenceHeaps(lowerHalf, upperHalf);
            double median = getMedian(lowerHalf, upperHalf);
            System.out.println(String.format("%.1f", median));
        }
    }

    private static void addToHeap(Queue<Integer> lowerHalf, Queue<Integer> upperHalf, int number) {
        if (lowerHalf.isEmpty() || number < lowerHalf.peek()) {
            lowerHalf.add(number);
        } else {
            upperHalf.add(number);
        }
    }

    private static void redolenceHeaps(Queue<Integer> lowerHalf, Queue<Integer> upperHalf) {
        Queue<Integer> larger = lowerHalf.size() < upperHalf.size() ? upperHalf : lowerHalf ;
        Queue<Integer> smaller = lowerHalf.size() < upperHalf.size() ? lowerHalf : upperHalf ;

        while (larger.size() - smaller.size() >= 2) {
            smaller.add(larger.poll());
        }
    }

    private static double getMedian(Queue<Integer> lowerHalf, Queue<Integer> upperHalf) {
        if (lowerHalf.size() == upperHalf.size()) {
            return ((double) lowerHalf.peek() + upperHalf.peek()) / 2;
        } else {
            return lowerHalf.size() > upperHalf.size() ? lowerHalf.peek() : upperHalf.peek();
        }
    }
}
