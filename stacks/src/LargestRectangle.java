import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LargestRectangle {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int size = in.nextInt();
        int arr[] = new int[size];
        input(arr);
        long ans = largestRectangle(arr);
        System.out.println(ans);
    }

    private static long largestRectangle(int arr[]){
        HashMap<Integer, Object> heightSet = hashing(arr);
        ArrayList<Integer> heights = createList(heightSet);
        HashMap<Integer, Integer> heightsIndexMap = hashing(heights);
        print(heights);
        HashMap<Integer, IndexRange> indexMap = hashing(arr, heights, heightsIndexMap);
        print(indexMap);

        return 0L;
    }

    private static HashMap<Integer, IndexRange> hashing(int arr[], ArrayList<Integer> heights,
                                                        HashMap<Integer, Integer> heightsIndexMap){

        HashMap<Integer, IndexRange> indexMap = new HashMap<>();
        for(int index = 0 ; index<arr.length ; index++){

            if(arr[index] == 1)
                continue;

            if(!indexMap.containsKey(arr[index])){
                int startIndex = index;

                if(index > 0){
                    if(arr[index-1] > arr[index]){
                        startIndex = index-1;
                        int listIndex = heightsIndexMap.get(arr[index]) + 1;
                        if(listIndex < heights.size()){
                            IndexRange towerRange = indexMap.get(arr[listIndex]);
                            if(towerRange.endIndex == index-1){
                                startIndex = towerRange.startIndex;
                            }
                        }
                    }
                }

                IndexRange range = new IndexRange(startIndex, (index != arr.length-1) ?
                        (arr[index+1] >= arr[index] ? index + 1 : index) : index);
                indexMap.put(arr[index], range);

            } else {
                IndexRange currentRange = indexMap.get(arr[index]);

                if(currentRange.endIndex > currentRange.startIndex){

                } else {
                    IndexRange range = new IndexRange(index, (index != arr.length-1) ?
                            (arr[index+1] >= arr[index] ? index + 1 : index) : index);
                    indexMap.put(arr[index], range);
                }
            }
        }
        return indexMap;
    }

    private static HashMap<Integer, Integer> hashing(ArrayList<Integer> heights){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int index = 0 ; index<heights.size() ; index++){
            map.put(heights.get(index), index);
        }
        return map;
    }

    private static ArrayList<Integer> createList(HashMap<Integer, Object> map){
        ArrayList<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Object> entry : map.entrySet()){
            list.add(entry.getKey());
        }
        return list;
    }

    private static HashMap<Integer, Object> hashing(int arr[]){
        HashMap<Integer, Object> map = new HashMap<>();
        Object obj = new Object();
        for(int index = 0 ; index<arr.length ; index++){
            if(!map.containsKey(arr[index]))
                map.put(arr[index], obj);
        }
        return map;
    }

    private static void input(int arr[]){
        for(int index = 0 ; index<arr.length ; index++){
            arr[index] = in.nextInt();
        }
    }

    private static void print(HashMap<Integer, IndexRange> map){
        System.out.println("");
        for(Map.Entry<Integer, IndexRange> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue().startIndex + " " + entry.getValue().endIndex);
        }
    }

    private static void print(ArrayList<Integer> list){
        System.out.println();
        for(int index = 0 ; index<list.size() ; index++){
            System.out.print(list.get(index) + " ");
        }
    }
}

class IndexRange{
    int startIndex;
    int endIndex;

    IndexRange(){}
    IndexRange(int startIndex, int endIndex){
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
}