import java.util.Scanner;

public class prime {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int number = in.nextInt();
        System.out.println(maxMultiple(number));
    }

    private static boolean isPrime(int number){
        for(int divisor=2 ; divisor<=number/2 ; divisor++){
            if(number % divisor == 0)
                return false;
        }
        return true;
    }

    private static int maxMultiple(int number){
        int root = (int)Math.sqrt(number);
        for(int index=root ; index>1 ; index--){
            if(number % index == 0)
                return number/index;
        }
        return number;
    }
}
