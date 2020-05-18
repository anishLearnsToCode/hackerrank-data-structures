// https://www.hackerrank.com/challenges/equal-stacks/problem

package stacks;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class EqualStacks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] blocks = new int[3], sum = new int[3];

        for (int index = 0 ; index < blocks.length ; index++) {
            blocks[index] = scanner.nextInt();
        }

        ArrayList<Stack<Integer>> stacks = new ArrayList<>();
        for (int index = 0 ; index < blocks.length ; index++) {
            int[] array = new int[blocks[index]];
            for (int count = 0 ; count < blocks[index] ; count++) {
                int element = scanner.nextInt();
                array[count] = element;
                sum[index] += element;
            }
            stacks.add(stackFrom(array));
        }

        while (!equal(sum)) {
            int maxStackIndex = maxIndex(sum);
            sum[maxStackIndex] -= stacks.get(maxStackIndex).pop();
        }

        System.out.println(sum[0]);
    }

    private static Stack<Integer> stackFrom(int[] array) {
        Stack<Integer> stack = new Stack<>();
        for (int index = array.length - 1 ; index >= 0 ; index--) {
            stack.push(array[index]);
        }
        return stack;
    }

    private static boolean equal(int[] array) {
        int element = array[0];
        for (int index = 1 ; index < array.length ; index++) {
            if (array[index] != element) {
                return false;
            }
        }

        return true;
    }

    private static int maxIndex(int[] array) {
        int max = array[0], result = 0;
        for (int index = 1 ; index < array.length ; index++) {
            if (array[index] > max) {
                max = array[index];
                result = index;
            }
        }

        return result;
    }
}
