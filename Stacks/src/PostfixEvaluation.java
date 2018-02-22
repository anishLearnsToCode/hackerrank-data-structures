import java.util.Scanner;
import java.util.Stack;

public class PostfixEvaluation {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter Postfix Expression : ");
        String postfixExpression = in.next();
        double ans = evaluate(postfixExpression);
        System.out.println(postfixExpression + " = " + ans);
    }

    private static double evaluate(String postfix){
        double ans = 0;
        Stack<Double> operand = new Stack<>();

        for(int index=0 ; index<postfix.length() ; index++){
            if(isOperand(postfix.charAt(index)))
                operand.push((double)integer(postfix.charAt(index)));

            //Is operator
            else{
                double b = operand.pop();
                double a = operand.pop();
                double operation = performOperation(a, b, postfix.charAt(index));
                operand.push(operation);
            }
        }

        return operand.peek();
    }

    private static boolean isOperand(Character operand){
        return !(operand == '+' || operand == '-' ||
                operand ==  '*' || operand == '/' ||
                operand == '(' || operand == ')'  ||
                operand == '^');
    }

    private static int integer(Character number){
        return (int)number - 48;
    }

    private static double performOperation(double a ,double b, Character operation){
        if(operation == '+')
            return a + b;
        else if(operation == '-')
            return a - b;
        else if(operation == '*')
            return a * b;
        else if(operation == '/')
            return a / b;
        return Math.pow(a, b);
    }
}
