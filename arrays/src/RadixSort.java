import java.util.Scanner;

public class RadixSort {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter array size : ");
        int size = in.nextInt();
        int arr[] = new int[size];
        input(arr);
        radixSort(arr);
        print(arr);
    }

    private static void input(int arr[]){
        for(int index=0 ; index<arr.length ; index++){
            arr[index] = in.nextInt();
        }
    }

    private static void radixSort(int arr[]){
        int maxElement = max(arr);
        int length = intLen(maxElement);

        for(int i=0 ; i<length ; i++){

            //Create 10 Buckets and Initialize
            Bucket[] buckets = new Bucket[10];
            for(int index=0 ; index<buckets.length ; index++){
                buckets[index] = new Bucket();
            }

            //Add to Buckets
            addToBuckets(arr, buckets, i);

            //ReInitialize - Array
            initialize(arr, buckets);
        }
    }

    private static void addToBuckets(int arr[], Bucket[] buckets, int position){
        for(int index=0 ; index<arr.length ; index++){
            int element = digitAt(arr[index], position);
            buckets[element].add(arr[index]);
        }
    }

    private static int digitAt(int number, int position){
        return (int)(number/Math.pow(10, position) + 10) % 10;
    }

    private static void initialize(int arr[], Bucket[] buckets){
        int arrayIndex = 0;
        for(int bucketIndex=0 ; bucketIndex<10 ; bucketIndex++){
            arrayIndex = initialize(arr, buckets[bucketIndex], arrayIndex);
        }
    }

    private static int initialize(int arr[], Bucket bucket, int index){
        for(int arrayIndex=index ; arrayIndex<bucket.size() + index  ; arrayIndex++){
            arr[arrayIndex] = bucket.get(arrayIndex - index);
        }
        return index + bucket.size();
    }

    private static int max(int arr[]){
        int max=arr[0];
        for(int index=1 ; index<arr.length ; index++){
            if(arr[index] > max)
                max = arr[index];
        }
        return max;
    }

    private static int intLen(int number){
        int length;
        for(length=0 ; number != 0 ; number /= 10, length++);
        return length;
    }

    private static void print(int arr[]){
        for(int index=0 ; index<arr.length ; index++){
            System.out.print(arr[index] + " ");
        }
    }
}
