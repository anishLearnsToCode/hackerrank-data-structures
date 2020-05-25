package arrays;

import java.util.HashMap;
import java.util.Scanner;

public class SparseArrays {

    private static String[] arr;
    private static HashMap<String, Integer> frequencyMap = new HashMap<>();
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int size = s.nextInt();
        arr = new String[size];
        input();
        hashing();

        int queries = s.nextInt();
        computeQueries(queries);
    }

    private static void computeQueries(int queries){
        for(int i=0 ; i<queries ; i++){
            String str = s.next();
            if(frequencyMap.containsKey(str))
                System.out.println(frequencyMap.get(str));
            else System.out.println("0");
        }
    }

    private static void hashing(){
        for(int i=0 ; i<arr.length ; i++){
            if(frequencyMap.containsKey(arr[i])){
                int previousFrequency = frequencyMap.get(arr[i]);
                frequencyMap.put(arr[i], previousFrequency + 1);
            } else {
                frequencyMap.put(arr[i], 1);
            }
        }
    }

    private static void input(){
        for(int i=0 ; i<arr.length ; i++){
            arr[i] = s.next();
        }
    }
}

