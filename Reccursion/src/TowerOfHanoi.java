import java.util.Scanner;

public class TowerOfHanoi {
    private static Scanner in = new Scanner(System.in);
    private static int moves=0;

    public static void main(String[] args) {
        System.out.print("Enter No. of Rings : ");
        int numberOfRings = in.nextInt();
        towerOfHanoi(numberOfRings, 'A', 'B', 'C');
        System.out.println("\n\nNo Of Moves : " + moves);
    }

    private static void towerOfHanoi(int numberOfRings, char from, char helper, char to){
        if(numberOfRings == 0){
            return;
        }
        moves++;
        towerOfHanoi(numberOfRings-1, from, to, helper);
        System.out.println("Move Ring " + numberOfRings + " from " + from + " to " + to);
        towerOfHanoi(numberOfRings-1, helper, from, to);
    }
}