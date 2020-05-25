package stacks;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class GameOfTwoStacks {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int games = in.nextInt();
        while (games-- > 0){
            runGame();
        }
    }

    private static void runGame(){
        int n = in.nextInt();
        int m = in.nextInt();
        long maxSum = in.nextLong();

        int arr[] = new int[n];
        for(int index = 0 ; index<n ; index++){
            arr[index] = in.nextInt();
        }
        Stack<Integer> left = new Stack<>();
        addToStack(left, arr);

        arr = new int[m];
        for(int index = 0 ; index<m ; index++){
            arr[index] = in.nextInt();
        }
        Stack<Integer> right = new Stack<>();
        addToStack(right, arr);

        //print(left);
        //print(right);

        int maxElementsRemoved = maxElementsRemovedFromStack(left, right, maxSum);
        System.out.println(maxElementsRemoved);
    }

    private static int maxElementsRemovedFromStack(Stack<Integer> left, Stack<Integer> right, long sum){
        ArrayList<Element> leftList = indexing(left, sum);
        ArrayList<Element> rightList = indexing(right, sum);

        if(leftList.size() > rightList.size()){
            ArrayList<Element> tempList = leftList;
            leftList = rightList;
            rightList = tempList;
        }
        print(leftList);
        print(rightList);

        if(leftList.size() == 0)
            return (rightList.size() == 0) ? 0 : rightList.get(rightList.size()-1).pieces;

        int maxPieces = rightList.get(rightList.size()-1).pieces;

        for(int index = 0 ; index<leftList.size() ; index++){
            long currentSum = leftList.get(index).sum;
            if(currentSum > sum) break;

            long otherSum = sum - currentSum;
            int otherIndex = parse(binarySearch(rightList, otherSum), otherSum, rightList.get(0).sum);
            maxPieces = (otherIndex != -1) ? Math.max(maxPieces, leftList.get(index).pieces + rightList.get(otherIndex).pieces) :
                    Math.max(maxPieces, leftList.get(index).pieces);
        }

        return maxPieces;
    }

    private static int parse(int index, long element, long first){
        if(index == 0 && element < first)
            return -1;

        return index;
    }

    private static int binarySearch(ArrayList<Element> list, long element){
        int tail, head;
        for(tail =0, head = list.size() ; tail < head-1 ; ){
            if(list.get((tail + head)/2).sum == element)
                return (tail+head)/2;

            else if(list.get((tail + head)/2).sum < element){
                tail = (tail+head)/2;
            }
            else {
                head = (head + tail)/2;
            }
        }

        return (tail + head)/2;
    }

    private static ArrayList<Element> indexing(Stack<Integer> stack, long sum){
        ArrayList<Element> list = new ArrayList<>();
        if(stack.elementAt(stack.size()-1) <= sum)
            list.add(new Element(stack.elementAt(stack.size()-1), 1));
        else {
            return new ArrayList<Element>();
        }

        int maxElements = 0;
        Long currentSum = (long)stack.peek();

        for(int index = stack.size()-2 ; index>=0 ; index--){
            int element = stack.elementAt(index);
            if(currentSum + element > sum) break;

            int previousPieces = list.get(list.size()-1).pieces;
            if(list.get(list.size()-1).sum == currentSum + element){
                list.remove(list.size()-1);
                list.add(new Element(currentSum , previousPieces + 1));
            } else {
                list.add(new Element(currentSum + element, previousPieces + 1));
            }
            currentSum += element;
        }

        return list;
    }

    private static void addToStack(Stack<Integer> stack, int arr[]){
        for(int index = arr.length -1 ; index>=0 ; index--){
            stack.push(arr[index]);
        }
    }

    private static void print(Stack<Integer> stack){
        System.out.println("");
        for(int index = 0 ; index<stack.size() ; index++){
            System.out.print(stack.elementAt(index) + " ");
        }
    }

    private static void print(ArrayList<Element> list){
        System.out.println("");
        for(int index = 0 ; index<list.size() ; index++){
            System.out.println(list.get(index).sum + " " + list.get(index).pieces);
        }
    }
}


class Element{

    long sum;
    int pieces;

    Element(){}
    Element(long sum , int pieces){
        this.sum = sum;
        this.pieces = pieces;
    }
}