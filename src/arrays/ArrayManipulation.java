package arrays;

import java.util.Scanner;

public class ArrayManipulation {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int length = SCANNER.nextInt();
        int queries = SCANNER.nextInt();
        long[] array = new long[length + 2];

        while (queries-- > 0) {
            int startIndex = SCANNER.nextInt();
            int endIndex = SCANNER.nextInt();
            int element = SCANNER.nextInt();
            array[startIndex] += element;
            array[endIndex + 1] -= element;
        }

        long sum = 0, max = Integer.MIN_VALUE;
        for (long element : array) {
            sum += element;
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
