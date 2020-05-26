// https://www.hackerrank.com/challenges/poisonous-plants/problem

package stacks;

import helper.Input;
import java.util.Stack;

public class PoisonousPlants {
    public static void main(String[] args) {
        int[] plants = Input.getArray();
        System.out.println(daysToSafety(plants));
    }

    private static int daysToSafety(int[] plants) {
        int minimumDays = 0;
        Stack<Info> stack = new Stack<>();
        stack.push(new Info(plants[0], 0, false, 0));
        for (int index = 1, days = 0 ; index < plants.length ; index++) {
            if (plants[index] > stack.peek().element) {
                stack.push(new Info(plants[index], index, true, days + 1));
                minimumDays = Math.max(minimumDays, days + 1);
                days = 0;
            } else {
                if (!stack.peek().removable) {
                    stack.push(new Info(plants[index], index, false, 0));
                    days = 0;
                } else {
                    int barrier = stack.peek().days;
                    while (stack.peek().removable && stack.peek().days <= barrier) {
                        Info info = stack.pop();
                        days = info.days;
                        minimumDays = Math.max(minimumDays, days);
                    }
                    index--;
                }
            }
        }
        return minimumDays;
    }

    private static class Info {
        int element;
        int index;
        boolean removable;
        int days;

        Info(int element, int index, boolean removable, int days) {
            this.element = element;
            this.index = index;
            this.removable = removable;
            this.days = days;
        }
    }
}
