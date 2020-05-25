package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DynamicArray {

    private static final Scanner s = new Scanner(System.in);
    private static final HashMap<Integer, ArrayList<Integer>> seqList = new HashMap<>();
    private static int lastAnswer = 0, N;

    public static void main(String[] args) {
        int queries;

        N = s.nextInt();
        queries = s.nextInt();

        performQueries(queries);
    }

    private static void performQueries(int queries) {
        int n, x, y;

        for(int i=0 ; i<queries ; i++){
            n = s.nextInt();
            x = s.nextInt();
            y = s.nextInt();

            if(n == 1)
                performQuery1(x, y);
            else
                performQuery2(x, y);
        }
    }

    private static void performQuery1(int x, int y) {
        int index = indexOf(x);
        if(seqList.containsKey(index)){
            ArrayList<Integer> smallList = seqList.get(index);
            smallList.add(y);
            seqList.put(index, smallList);
        } else {
            ArrayList<Integer> smallList = new ArrayList<>();
            smallList.add(y);
            seqList.put(index, smallList);
        }
    }

    private static void performQuery2(int x, int y){
        int index = indexOf(x);
        ArrayList<Integer> smallList = seqList.get(index);
        lastAnswer = smallList.get(y%smallList.size());
        System.out.println(lastAnswer);
    }

    private static int indexOf(int x){
        return (x^lastAnswer)%N ;
    }
}
