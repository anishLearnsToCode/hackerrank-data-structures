import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArrayManipulation_Editorial {

    private static Scanner s = new Scanner(System.in);
    private static HashMap<Integer, Long> valueMap = new HashMap<>();

    public static void main(String[] args) {
        int size = s.nextInt();
        int queries = s.nextInt();

        computeQueries(queries, size);
        print(valueMap);
        long ans = maxValue();
        System.out.println(ans);
    }

    private static void computeQueries(int queries, int size){
        for(int i=0 ; i<queries ; i++){
            int a = s.nextInt();
            int b = s.nextInt();
            long k = s.nextLong();
            add(a, b, k, size);
        }
    }

    private static void add(int startIndex, int endIndex, long k, int size){
        if(valueMap.containsKey(startIndex-1)) {
            long previousValue = valueMap.get(startIndex - 1);
            valueMap.put(startIndex - 1, previousValue + k);
            if (endIndex != size && valueMap.containsKey(endIndex)) {
                previousValue = valueMap.get(endIndex);
                valueMap.put(endIndex, previousValue - k);
            } else if(endIndex != size && !valueMap.containsKey(endIndex)){
                valueMap.put(endIndex, -k);
            }
        } else {
            valueMap.put(startIndex-1, k);
            if(endIndex != size)
                valueMap.put(endIndex, -k);
        }
    }

    private static void print(HashMap<Integer, Long> map){
        for(Map.Entry<Integer,Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private static long maxValue(){
        long max=0, sum=0;
        for(Map.Entry<Integer, Long> entry : valueMap.entrySet()){
            sum += entry.getValue();
            max = Math.max(max, sum);
        }
        return max;
    }
}
