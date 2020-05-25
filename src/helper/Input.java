package helper;

import java.util.Scanner;

public class Input {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int[][] getMatrix(int rows, int columns) {
        int[][] data = new int[rows][columns];
        for (int row = 0 ; row < rows ; row++) {
            for (int column = 0 ; column < columns ; column++) {
                data[row][column] = SCANNER.nextInt();
            }
        }
        return data;
    }

    public static int getLength() {
        return SCANNER.nextInt();
    }

    public static int[] getArray() {
        return getArray(getLength());
    }

    public static int[] getArray(int length) {
        int[] array = new int[length];
        for (int index = 0 ; index < array.length ; index++) {
            array[index] = SCANNER.nextInt();
        }
        return array;
    }
}
