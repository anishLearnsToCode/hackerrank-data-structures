package arrays;

import java.util.Scanner;

public class LeftRotation {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static int array[];

    public static void main(String[] args) {
        int size = SCANNER.nextInt();
        int leftRotations = SCANNER.nextInt();

        array = new int[size];
        input(array);

        rotate(leftRotations);
        print(array);
    }

    private static void rotate(int leftRotations){
        leftRotations = leftRotations % array.length;
        int rightRotations = array.length - leftRotations;
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
        int first = array[0];
        for(int i=0 ; i<array.length-1 ; i++){
            array[i] = array[i+1];
        }
        array[array.length-1] = first;
    }

    private static void rightRotate(int rotations){
        for(int i=0 ; i<rotations ; rightRotate(), i++);
    }

    private static void rightRotate(){
        int last = array[array.length-1];
        for(int i=array.length-1 ;i>0 ; i--){
            array[i] = array[i-1];
        }
        array[0] = last;
    }

    private static void print(int arr[]){
        for(int index =0 ; index<arr.length ; System.out.print(arr[index] + " ") , index++);
    }

    private static void input(int arr[]){
        for(int index =0 ; index<arr.length ; arr[index] = SCANNER.nextInt(), index++);
    }
}
