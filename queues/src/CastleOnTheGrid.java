import java.util.Scanner;

public class CastleOnTheGrid {

    enum Direction{
        HORIZONTAL, VERTICAL, START;
    }

    private static Scanner in = new Scanner(System.in);
    private static int arr[][];

    public static void main(String[] args) throws InterruptedException {
        int n = in.nextInt();
        arr = new int[n][n];
        initialize(arr);
        String[] grid = new String[n];
        for(int row=0 ; row<n ; row++){
            grid[row] = in.next();
        }
        int xStart = in.nextInt();
        int yStart = in.nextInt();
        int xEnd = in.nextInt();
        int yEnd = in.nextInt();

        arr[xStart][yStart] = 1;

        int ans = minPathLength(grid, xStart, yStart, xEnd, yEnd, Direction.START);
        System.out.println("Ans : " + ans);
    }

    private static int ans = Integer.MAX_VALUE;
    private static int minPathLength(String[] grid, int row, int col, int toRow, int toCol, Direction direction) throws InterruptedException {

//        print(arr);
//        System.out.println("");
//        Thread.sleep(1600);

        if(row == toRow && col == toCol) {
            arr[row][col] = 0;
            return 0;
        }

        if(row-1 >= 0 && grid[row-1].charAt(col) != 'X' && arr[row-1][col] != 1){
            int factor = direction == Direction.START ? 1 : (
                    direction == Direction.VERTICAL ? 0 : 1
                    );
            arr[row-1][col] = 1;
            ans = Math.min(ans, factor + minPathLength(grid, row-1, col, toRow, toCol, Direction.VERTICAL));
        }
        if(col+1 < grid[0].length() && grid[row].charAt(col+1) != 'X' && arr[row][col+1] != 1){
            int factor = direction == Direction.START ? 1 : (
                    direction == Direction.VERTICAL ? 1 : 0
                    );
            arr[row][col+1] = 1;
            ans = Math.min(ans, factor + minPathLength(grid, row, col+1, toRow, toCol, Direction.HORIZONTAL));
        }
        if(row+1 < grid.length && grid[row+1].charAt(col) != 'X' && arr[row+1][col] != 1){
            int factor = direction == Direction.START ? 1 : (
                    direction == Direction.VERTICAL ? 0 : 1
            );
            arr[row+1][col] = 1;
            ans = Math.min(ans, factor + minPathLength(grid, row+1, col, toRow, toCol, Direction.VERTICAL));
        }
        if(col-1 >= 0 && grid[row].charAt(col-1) != 'X' && arr[row][col-1] != 1){
            int factor = direction == Direction.START ? 1 : (
                    direction == Direction.VERTICAL ? 1 : 0
            );
            arr[row][col-1] = 1;
            ans = Math.min(ans, factor + minPathLength(grid, row, col-1, toRow, toCol, Direction.HORIZONTAL));
        }

        arr[row][col] = 0;
        return ans;
    }

    private static void initialize(int arr[][]){
        for(int row =0; row<arr.length ; row++){
            for(int col=0 ; col<arr[row].length ; col++){
                arr[row][col] = 0;
            }
        }
    }

    private static void print(int arr[][]){
        for(int row=0 ; row<arr.length ; System.out.println(), row++){
            for(int col=0 ; col<arr[row].length ; col++)
                System.out.print(arr[row][col]);
        }
    }
}
