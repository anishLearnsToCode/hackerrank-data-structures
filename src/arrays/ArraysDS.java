package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class ArraysDS {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length = scanner.nextInt();
        int[] array = getArray(length);
        System.out.println(Arrays.toString(reverse(array)));
    }

    private static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = scanner.nextInt();
        }
        return array;
    }

    private static int[] reverse(int[] array) {
        int[] result = new int[array.length];
        for (int index = 0 ; index < array.length ; index++) {
            result[index] = array[array.length - 1 - index];
        }
        return result;
    }
}
