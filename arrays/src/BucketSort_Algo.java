import java.util.ArrayList;
import java.util.Scanner;

public class BucketSort_Algo {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Size of Array : ");
        int size = in.nextInt();
        int arr[] = new int[size];
        Bucket buckets[] = new Bucket[size/5 + 1];

        for(int index=0 ; index<size ; index++){
            arr[index] = in.nextInt();
        }
        arr = bucketSort(arr, buckets);
        print(arr);
    }

    private static int[] bucketSort(int arr[], Bucket buckets[]){
        for(int index=0 ; index<buckets.length ; index++){
            buckets[index] = new Bucket();
        }
        addToBucket(arr, buckets);
        sortBuckets(buckets);
        return sortArray(arr.length, buckets);
    }

    private static void sortBuckets(Bucket[] buckets){
        for(int index=0 ; index<buckets.length ; index++){
            buckets[index].sort();
        }
    }

    private static int[] sortArray(int size, Bucket[] buckets){
        int ans[] = new int[size];
        for(int index=0, arrayIndex=0 ; index<buckets.length ; index++){
            arrayIndex = addTo(ans, buckets[index], arrayIndex);
        }
        return ans;
    }

    private static int addTo(int arr[], Bucket bucket, int index){
        return bucket.addTo(arr, index);
    }

    private static void addToBucket(int arr[], Bucket[] buckets){
        int minVal = min(arr);
        int maxVal = max(arr);
        final int bucketSize = buckets.length;
        final double range = maxVal - minVal + 1;

        for(int index=0 ; index<arr.length ; index++){
            int bucketIndex = (int)(bucketSize * (double)(arr[index] - minVal) / (range));
            buckets[bucketIndex].add(arr[index]);
        }
    }

    private static int max(int arr[]){
        int maxVal, index;
        for(index=0, maxVal=arr[0] ; index<arr.length ; index++){
            if(arr[index] > maxVal)
                maxVal = arr[index];
        }
        return maxVal;
    }

    private static int min(int arr[]){
        int minVal, index;
        for(index=0, minVal = arr[0] ; index<arr.length ; index++){
            if(arr[index] < minVal)
                minVal = arr[index];
        }
        return minVal;
    }

    private static void print(int arr[]){
        for(int index=0 ; index<arr.length ; index++){
            System.out.print(arr[index] + " ");
        }
    }
}

class Bucket{
    private ArrayList<Integer> list;

    Bucket(){
        list = new ArrayList<>();
    }
    Bucket(int size){
        list = new ArrayList<>(size);
    }

    public void add(Integer data){
        list.add(data);
    }

    public int size(){
        return list.size();
    }

    public int get(int index){
        return list.get(index);
    }

    public void sort(){
        list = mergeSort(list);
    }

    private ArrayList<Integer> mergeSort(ArrayList<Integer> list){
        if(list.size() <= 1)
            return list;

        ArrayList<Integer> smallList1 = new ArrayList<>();
        ArrayList<Integer> smallList2 = new ArrayList<>();
        int index=0;

        for(; index<list.size()/2 ; index++){
            smallList1.add(list.get(index));
        }
        for( ; index<list.size() ; index++){
            smallList2.add(list.get(index));
        }

        ArrayList<Integer> ans1 = mergeSort(smallList1);
        ArrayList<Integer> ans2 = mergeSort(smallList2);

        return merge(ans1, ans2);
    }

    private ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2){
        int i, j, t;
        ArrayList<Integer> ans = new ArrayList<>();

        for(i=0, j=0 ; i<=list1.size() && j<=list2.size() ; ){
            if(i == list1.size()){
                for(t=0; t<list2.size() - j ; ans.add(list2.get(t+j)), t++);
                break;
            } else if(j == list2.size()){
                for(t=0 ; t< list1.size() - i ; ans.add(list1.get(t+i)), t++);
                break;
            }

            if((int)list1.get(i) <= (int)list2.get(j)){
                ans.add(list1.get(i));
                i++;
            } else {
                ans.add(list2.get(j));
                j++;
            }
        }
        return ans;
    }

    public int addTo(int arr[], int index){
        for(int count=0 ; count<list.size() ; index++, count++){
            arr[index] = (int)list.get(count);
        }
        return index;
    }
}
