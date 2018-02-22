import java.util.Scanner;

public class ArrayManipulation {
    private static long arr[];
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int size = s.nextInt();
        arr = new long[size];
        int queries = s.nextInt();
        computeQueries(queries);

        long ans = max();
        System.out.println(ans);
    }

    private static long max(){
        long ans = Long.MIN_VALUE;
        for(int index =0 ; index<arr.length ; index++){
            ans = Math.max(ans, arr[index]);
        }
        return ans;
    }

    private static void computeQueries(int queries){
        for(int i=0 ; i<queries ; i++){
            int startIndex = s.nextInt();
            int endIndex = s.nextInt();
            long value = s.nextLong();
            add(startIndex, endIndex, value);
        }
    }

    private static void add(int startIndex, int endIndex, long value){
        for(int index = startIndex-1 ; index<endIndex ; index++){
            arr[index] += value;
        }
    }

    private static void print(){
        for(int index =0 ; index<arr.length ; index++){
            System.out.print(arr[index] + " ");
        }
    }
}
