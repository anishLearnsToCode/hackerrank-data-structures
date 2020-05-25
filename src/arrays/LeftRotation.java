package arrays;

import java.util.Scanner;

public class LeftRotation {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static int arr[];

    public static void main(String[] args) {
        int size = SCANNER.nextInt();
        int leftRotations = SCANNER.nextInt();

        arr = new int[size];
        input(arr);

        rotate(leftRotations);
        print(arr);
    }

    private static void rotate(int leftRotations){
        leftRotations = leftRotations % arr.length;
        int rightRotations = arr.length - leftRotations;
        int rotations = Math.min(leftRotations, rightRotations);

        if(rotations == leftRotations)
            leftRotate(rotations);
        else
            rightRotate(rotations);
    }

    private static void leftRotate(int rotations){
        for(int i=0 ; i<rotations ; leftRotate(), i++);
    }

    private static void leftRotate(){
        int first = arr[0];
        for(int i=0 ; i<arr.length-1 ; i++){
            arr[i] = arr[i+1];
        }
        arr[arr.length-1] = first;
    }

    private static void rightRotate(int rotations){
        for(int i=0 ; i<rotations ; rightRotate(), i++);
    }

    private static void rightRotate(){
        int last = arr[arr.length-1];
        for(int i=arr.length-1 ;i>0 ; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = last;
    }

    private static void print(int arr[]){
        for(int index =0 ; index<arr.length ; System.out.print(arr[index] + " ") , index++);
    }

    private static void input(int arr[]){
        for(int index =0 ; index<arr.length ; arr[index] = SCANNER.nextInt(), index++);
    }
}
