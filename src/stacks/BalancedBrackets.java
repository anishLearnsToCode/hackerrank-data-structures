// https://www.hackerrank.com/challenges/balanced-brackets/problem

package stacks;

import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();

        while (queries-- > 0) {
            String string = scanner.next();
            System.out.println(isBalanced(string));
        }
    }

    private static String isBalanced(String string) {
        return isBalancedBrackets(string) ? "YES" : "NO" ;
    }

    private static boolean isBalancedBrackets(String string) {
        Stack<Character> brackets = new Stack<>();
        for (int index = 0 ; index < string.length() ; index++) {
            char character = string.charAt(index);
            if (isOpeningBrace(character)) {
                brackets.push(character);
            } else if (isClosingBrace(character)) {
                if (!brackets.isEmpty() && brackets.peek() == inverse(character)) {
                    brackets.pop();
                } else {
                    return false;
                }
            }
        }

        return brackets.isEmpty();
    }

    private static boolean isOpeningBrace(char character) {
        return character == '(' || character == '{' || character == '[';
    }

    private static boolean isClosingBrace(char character) {
        return character == ')' || character == '}' || character == ']';
    }

    private static char inverse(char character) {
        switch (character) {
            case '(' : return ')';
            case '{' : return '}';
            case '[' : return ']';
            case ')' : return '(';
            case '}' : return '{';
            case ']' : return '[';
        }
        return '-';
    }
}
