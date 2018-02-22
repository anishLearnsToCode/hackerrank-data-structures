import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class TripletArray {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int size = s.nextInt();
        int arr[] = new int[size];
        input(arr);
        boolean ans = tripletExistsIn(arr);
        System.out.println(ans);
    }

    private static boolean tripletExistsIn(int arr[]){
        ArrayList<Stack<Integer>> numStack = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        numStack.add(stack);
        boolean flag = false;

        for(int index =0 ; index<arr.length ; index++){
            for(int j =0  ; j<numStack.size() ; j++){
                stack = numStack.get(j);
                operate(stack, arr[index]);
                if(stack.size() > 1 && stack.elementAt(0) > arr[index]){
                    Stack<Integer> tempStack = new Stack<>();
                    tempStack.push(arr[index]);
                    numStack.add(tempStack);
                }
                if(stack.size() >= 3){
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    private static void operate(Stack<Integer> stack, int data){
        if(data > stack.peek()){
            stack.push(data);
        } else if(data < stack.peek()){
            if((stack.size() > 1) && (stack.elementAt(stack.size()-1) < data)){
                stack.pop();
                stack.push(data);
            } else if(stack.size() > 1 && stack.elementAt(stack.size()-1) > data){

            } else if(stack.size() == 1 && stack.peek() != data){
                stack.pop();
                stack.push(data);
            }
        }
    }

    private static void input(int arr[]){
        for(int index = 0 ; index<arr.length ; index++){
            arr[index] = s.nextInt();
        }
    }
}
