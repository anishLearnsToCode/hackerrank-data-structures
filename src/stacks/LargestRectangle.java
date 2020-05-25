// https://www.hackerrank.com/challenges/largest-rectangle/problem

package stacks;

import helper.Input;
import java.util.Stack;

public class LargestRectangle {
    public static void main(String[] args) {
        int[] array = Input.getArray();
        long area = maxArea(array);
        System.out.println(area);
    }

    private static long maxArea(int[] heights) {
        Stack<Info> stack = new Stack<>();
        long area = 0;

        for (int index = 0 ; index < heights.length ; index++) {
            while (!stack.isEmpty() && (heights[index] < stack.peek().element)) {
                Info top = stack.pop();
                area = Math.max(area, calculateArea(top.element, stack, index));
            }
            stack.push(new Info(heights[index], index));
        }

        while (!stack.isEmpty()) {
            Info top = stack.pop();
            area = Math.max(area,calculateArea(top.element, stack, heights.length));
        }

        return area;
    }

    private static long calculateArea(int element, Stack<Info> stack, int index) {
        int startIndex = stack.isEmpty() ? -1 : stack.peek().index;
        return element * (index - startIndex - 1);
    }

    private static class Info {
        int element;
        int index;

        Info(int element, int index) {
            this.element = element;
            this.index = index;
        }
    }
}
