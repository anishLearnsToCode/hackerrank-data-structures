package queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueriesWithFixedLength {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        int queries = scanner.nextInt();
        int[] array = getArray(length);

        while (queries-- > 0) {
            int query = scanner.nextInt();
            System.out.println(minimumIn(array, query));
        }
    }

    private static int minimumIn(int[] array, int subLength) {
        Queue<Integer> window = new LinkedList<>();
        addElementsIn(window, subLength, array);
        int maximum = window.stream().max(Integer::compareTo).get();
        int minimum = maximum;

        for (int index = subLength ; index < array.length ; index++) {
            int removed = window.poll();
            int inserted = array[index];
            if (inserted >= maximum) {
                maximum = inserted;
            } else if (removed == maximum) {
                maximum = window.stream().max(Integer::compareTo).get();
            }
            window.add(inserted);
            minimum = Math.min(minimum, maximum);
        }

        return minimum;
    }

    private static void addElementsIn(Queue<Integer> window, int length, int[] array) {
        for (int index = 0 ; index < length ; index++) {
            window.add(array[index]);
        }
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
        return array;
    }
}
