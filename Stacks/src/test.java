import java.util.Scanner;

public class test {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        char ch = in.next().charAt(0);
        System.out.println((int)ch);
    }

    private static boolean isPrime(int number){
        for(int divisor=2 ; divisor<=number/2 ; divisor++){
            if(number % divisor == 0)
                return false;
        }
        return true;
    }
}
