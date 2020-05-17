import java.util.Scanner;

enum MethodType{
    Recursive, Dynamic;
}

public class FibonacciNumber {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter Number : ");
        int number = in.nextInt();
        long ans = fibonacci(number, MethodType.Recursive);
        System.out.println(number);
    }

    private static long fibonacci(int number, MethodType methodType){
        if(methodType == MethodType.Recursive)
            return fibonacci(number);
        return 0;
    }

    private static long fibonacci(int number) {
        if (number == 0) return 1;
        return fibonacci(number - 1) + fibonacci(number - 2);
    }
}
