// https://www.hackerrank.com/challenges/pair-sums/problem

package advanced;

import java.util.Scanner;

public class PairSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] array = new int[length];

        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }

        long sum = array[array.length - 1], result = 0;
        for (int index = array.length - 2 ; index >= 0 ; sum += array[index], index--) {
            result += array[index] * sum;
        }
        System.out.println(result);
    }
}
