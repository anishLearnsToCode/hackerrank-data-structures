import java.util.Scanner;

public class LargestRectangle_Unoptimized {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int size = in.nextInt();
        int arr[] = new int[size];
        for(int index = 0 ; index<arr.length ; index++){
            arr[index] = in.nextInt();
        }

        long ans = MaxValue(arr);
        System.out.println("\n" + ans);
    }

    private static long MaxValue(int arr[]){
        Node table[][] = DPTable(arr);
        print(table);
        return MaxValue(table);
    }

    private static long MaxValue(Node[][] table){
        long ans = 0L;
        for(int i=0 ; i<table.length ; i++){
            for(int row =0 ; row<table.length-i ; row++){
                int col = i + row;
                ans = Math.max(ans, table[row][col].min * table[row][col].sum);
            }
        }
        return ans;
    }

    private static Node[][] DPTable(int arr[]){
        Node[][] table = new Node[arr.length][arr.length];

        //First Diagonal
        for(int i=0 ; i<table.length ; i++){
            table[i][i] = new Node(1, arr[i]);
        }

        //Rest Of the Diagonals
        for(int i=0 ; i<table.length-1 ; i++){
            for(int j=0 ; j<arr.length-i-1 ; j++){
                int row = j, col = 1+i+j;
                long sum = table[row][col-1].sum + table[col][col].sum;
                int min = Math.min(table[row][col-1].min, table[col][col].min);
                table[row][col] = new Node(sum, min);
            }
        }

        return table;
    }

    private static void print(Node[][] table){
        for(int i=0 ; i<table.length ; i++){
            System.out.println();
            for(int j=0 ; j<table[i].length ; j++){
                if(table[i][j] != null)
                System.out.print(table[i][j].sum + " ");
                else System.out.print("  ");
            }
        }
    }
}

class Node{
    long sum;
    int min;

    Node(){}
    Node(long sum , int min){
        this.min = min;
        this.sum = sum;
    }
}
