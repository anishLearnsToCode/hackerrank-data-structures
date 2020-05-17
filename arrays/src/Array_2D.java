import java.util.Scanner;

public class Array_2D {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int arr[][] = new int[6][6];

        for(int i=0 ; i<arr.length ; i++){
            for(int j=0 ; j<arr.length ; j++){
                arr[i][j] = s.nextInt();
            }
        }

        int ans = maxHourGlassSum(arr);
        System.out.println(ans);
    }

    private static int maxHourGlassSum(int arr[][]){
        int ans=0;
        for(int i=0 ; i<4 ; i++){
            for(int j=0, maxVal =0 ; j<4 ; j++){
                maxVal = hourGlassValue(i, j, arr);
                ans = Math.max(ans, maxVal);
            }
        }
        return ans;
    }

    private static int hourGlassValue(int row, int column, int arr[][]){
        int sum, i;
        for(i=0, sum=0 ; i<3 ; sum += arr[row][column+i] + arr[row+2][column+i], i++);
        sum += arr[row+1][column+1];
        return sum;
    }
}
