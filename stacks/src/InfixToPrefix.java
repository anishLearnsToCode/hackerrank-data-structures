import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class InfixToPrefix {
    private static Scanner in = new Scanner(System.in);
    private static HashMap<Character, Integer> priorityMap = new HashMap<>();

    public static void main(String[] args) {
        //Inputting Infix Expression
        System.out.print("Enter Infix Expression : ");
        String infixExpression = in.next();
        infixExpression = reverse(infixExpression);                             //Reversing Infix Expression

        //Calculating Postfix Expression
        String postfixExpression = postfix(infixExpression);
        String prefixExpression = reverse(postfixExpression);                   //Reversing Postfix Expression
        System.out.println("Prefix Expression : " + prefixExpression);
    }

    private static String reverse(String expression){
        String ans = "";
        for(int index=0 ; index<expression.length() ; index++){
            if(isOperand(expression.charAt(index)))
                ans = expression.charAt(index) + ans;

            //Is Operator or bracket
            else{
                if(isBracket(expression.charAt(index)))
                    ans = reverse(expression.charAt(index)) + ans;
                //Is Operand
                else{
                    ans = expression.charAt(index) + ans;
                }
            }
        }
        return ans;
    }

    private static String reverse(Character bracket){
        if(isOpenBracket(bracket))
            return ")";
        return "(";
    }

    private static String postfix(String infix){
        initializePriorityMap();
        Stack<Character> operator = new Stack<>();
        String postfixExpression = "";

        for(int index=0 ; index<infix.length() ; index++){
            //System.out.println(postfixExpression);

            if(isOperand(infix.charAt(index)))
                postfixExpression += infix.charAt(index) + " ";

                //Is Operator or Bracket
            else {
                if(isOpenBracket(infix.charAt(index)))
                    operator.push(infix.charAt(index));
                else if(isClosedBracket(infix.charAt(index))){
                    //Run while encounters open bracket and add to postfix expression
                    while (true){
                        Character operand = operator.pop();
                        if(operand == '(')
                            break;
                        else postfixExpression += operand + " ";
                    }
                } //Encountered Operator
                else {
                    //Run while operator is higher precedence than stack.peek()
                    while(operator.size() !=0 && priority(infix.charAt(index),operator.peek()) && isNotBracket(operator.peek())){
                        Character operand = operator.pop();
                        postfixExpression += operand + " ";
                    } operator.push(infix.charAt(index));
                }
            }

            //Add Operator Stack to Postfix Expression
        }
        postfixExpression = addToExpression(operator, postfixExpression);

        return postfixExpression;
    }

    private static boolean isNotBracket(Character bracket){
        return !(bracket == '(' || bracket == ')');
    }

    private static boolean isOpenBracket(Character bracket){
        return bracket == '(';
    }

    private static boolean isBracket(Character bracket){
        return isClosedBracket(bracket) || isOpenBracket(bracket);
    }

    private static boolean isClosedBracket(Character bracket){
        return bracket == ')';
    }

    private static boolean isOperand(Character operand){
        return !(operand == '+' || operand == '-' ||
                operand ==  '*' || operand == '/' ||
                operand == '(' || operand == ')'  ||
                operand == '^');
    }

    private static void initializePriorityMap(){
        priorityMap.put('(', 3); priorityMap.put(')', 3);
        priorityMap.put('^', 2);
        priorityMap.put('*', 1); priorityMap.put('/', 1);
        priorityMap.put('+', 0); priorityMap.put('-', 0);
    }

    private static boolean priority(Character operator, Character stackOperator){
        return priorityMap.get(operator) < priorityMap.get(stackOperator);
    }

    private static String addToExpression(Stack<Character> operator, String postfix){
        while (!operator.isEmpty()){
            postfix += operator.pop() + " ";
        }
        return postfix;
    }
}
