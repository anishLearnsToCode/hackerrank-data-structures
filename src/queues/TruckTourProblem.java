package queues;

import java.util.Scanner;

public class TruckTourProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] petrol = new int[length];
        int[] distance = new int[length];
        for (int index = 0 ; index < length ; index++) {
            petrol[index] = scanner.nextInt();
            distance[index] = scanner.nextInt();
        }

        System.out.println(startIndex(petrol, distance));
    }

    private static int startIndex(int[] petrol, int[] distance) {
        int mod = petrol.length;
        for (int index = 0 ; index < petrol.length - 1 ; index++) {
            int current = 0, count = 0;
            boolean answer = true;
            for (int j = index  ; count < 2; j = (j + 1) % mod) {
                if (j == index) {
                    count++;
                }

                current += (petrol[j] - distance[j]);

                if (current < 0) {
                    answer = false;
                    break;
                }
            }

            if (answer) {
                return index;
            }
        }
        return mod - 1;
    }
}
