import java.util.Scanner;

public class FibonacciNumberRecursiveFormula {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int number = in.nextInt();
        for(int i =0 ; i<number ; i++){
            System.out.println(fibonacci(i));
        }
    }

    private static double fibonacci(int number){
        return (1/Math.sqrt(5)) * ( Math.pow((1 + Math.sqrt(5) / 2), number) - Math.pow((1 - Math.sqrt(5) / 2) ,number)) ;
    }
}
