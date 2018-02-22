import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        int arr[] = new int[size];
        for(int index =0 ; index<arr.length ; index++){
            arr[index] = s.nextInt();
        }
        int element = s.nextInt();
        int ans = parse(binarySearch(arr, element), element, arr[0]);
        System.out.println(ans);
        if(ans != -1) System.out.println(arr[ans]);
    }

    private static int parse(int index, int element, int first){
        if(index == 0 && element < first)
            return -1;

        return index;
    }

    private static int binarySearch(int arr[], int element){
        int tail, head;
        for(tail =0, head = arr.length ; tail < head-1 ; ){
            if(arr[(tail + head)/2] == element)
                return (tail+head)/2;

            else if(arr[(tail + head)/2] < element){
                tail = (tail+head)/2;
            }
            else {
                head = (head + tail)/2;
            }
        }

        return (tail + head)/2;
    }
}
