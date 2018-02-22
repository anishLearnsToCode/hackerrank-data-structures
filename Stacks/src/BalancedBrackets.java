import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int queries = in.nextInt();
        while (queries-- > 0){
            String bracketString = in.next();
            String ans = isBalancedBracketString(bracketString);
            System.out.println(ans);
        }
    }

    private static String isBalancedBracketString(String bracketString){
        boolean ans = true;
        Stack<Character> bracketStack = new Stack<>();

        for(int index = 0 ; index<bracketString.length() ; index++){
            if(isOpenBracket(bracketString.charAt(index))){
                bracketStack.push(bracketString.charAt(index));
            } else if(bracketStack.size() > 0){
                if(isBracketPair(bracketStack.peek(), bracketString.charAt(index) )){
                    bracketStack.pop();
                } else {
                    ans = false;
                    break;
                }
            } else {
                ans = false;
                break;
            }
        }
        if(!bracketStack.isEmpty()) ans = false;

        if(ans) return "YES";
        return "NO";
    }

    private static boolean isOpenBracket(Character ch){
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static boolean isBracketPair(char ch1, char ch2){
        return (ch1 == '(' && ch2 == ')') ||
                (ch1 == '[' && ch2 == ']') ||
                (ch1 == '{' && ch2 == '}');
    }
}
