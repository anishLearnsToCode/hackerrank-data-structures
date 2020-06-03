package queues;

import java.util.Scanner;

public class DownToZeroII {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            int number = scanner.nextInt();
            System.out.println(ReductionSteps.minimumSteps(number));
        }
    }

    private static class ReductionSteps {
        private static final int length = 1_000_001;
        private static final int[] result = new int[length];

        static {
            result[0] = 0;
            result[1] = 1;
            result[2] = 2;
            result[3] = 3;

            for (int index = 2 ; index < length ; index++) {
                if (result[index] == 0 || result[index] > (result[index - 1] + 1)) {
                    result[index] = result[index - 1] + 1;
                }

                for (int j = 2 ; j <= index && j * index < length ; j++) {
                    if (result[index * j] == 0 || result[index * j] > (result[index] + 1)) {
                        result[index * j] = result[index] + 1;
                    }
                }
            }
        }

        private static int minimumSteps(int number) {
            return result[number];
        }
    }
}
