import java.util.Scanner;
import java.util.Stack;

public class LargestRectangle_Editorial {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int size = in.nextInt();
        int arr[] = new int[size + 1];
        input(arr);
        arr[size] = 0;

        long ans = maxRectangle(arr);
        System.out.println(ans);
    }

    private static long maxRectangle(int arr[]){
        Stack<Rectangle> stack = new Stack<Rectangle>();
        long ans =0;
        stack.push(new Rectangle(arr[0], 0));

        for(int index=1 ; index<arr.length ; index++){
            while (!stack.isEmpty() && arr[index] < stack.peek().data){
                Rectangle rectangle = stack.pop();
                int previousIndex = stack.isEmpty() ? -1 : stack.peek().index;
                int range = index - rectangle.index ;
                ans = Math.max(ans, range * rectangle.data);
            }
            stack.push(new Rectangle(arr[index], index));
        }
        return ans;
    }

    private static void input(int arr[]){
        for(int index=0 ; index<arr.length-1 ; index++){
            arr[index] = in.nextInt();
        }
    }
}

class Rectangle{
    int data;
    int index;
    Rectangle(){}
    Rectangle(int data, int index){
        this.data = data;
        this.index = index;
    }
}
