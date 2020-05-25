package arrays;

import helper.Input;

public class TwoDArrays {
    public static void main(String[] args) {
        int[][] matrix = Input.getMatrix(6, 6);
        System.out.println(maxHourGlassSum(matrix));
    }

    private static int maxHourGlassSum(int[][] data) {
        int result = Integer.MIN_VALUE;
        for (int row = 0 ; row < 4 ; row++) {
            for (int column = 0 ; column < 4 ; column++) {
                result = Math.max(result, hourGlassSum(data, row, column));
            }
        }
        return result;
    }

    private static int hourGlassSum(int[][] data, int row, int column) {
        int sum = 0;
        for (int index = column ; index < column + 3 ; sum += data[row][index] + data[row+2][index], index++);
        return sum + data[row + 1][column + 1];
    }
}
