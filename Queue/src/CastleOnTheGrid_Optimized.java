import java.util.LinkedList;
import java.util.Scanner;

public class CastleOnTheGrid_Optimized {

    private static Scanner in = new Scanner(System.in);

    private static int row, col, arr[][];
    private static String[] grid;

    public static void main(String[] args) {
        int n = in.nextInt();
        grid = new String[n];
        arr = new int[n][n];
        for(int row=0 ; row<n ; row++){
            grid[row] = in.next();
        }
        int xStart = in.nextInt();
        int yStart = in.nextInt();
        row = in.nextInt();
        col = in.nextInt();
        int ans = minPathLength(xStart, yStart);
        System.out.println(ans);
        System.out.println();
        print(arr);
    }

    private static LinkedList<Position> queue;
    private static int minPathLength(int rowCurrent, int colCurrent){
        queue = new LinkedList<>();
        queue.push(new Position(0, rowCurrent, colCurrent, Direction.START));
        arr[0][0] = 0;

        while (!queue.isEmpty()){
            Position currentPosition = queue.pop();
            if(currentPosition.direction == Direction.START){
                int ans = vertical(currentPosition);
                    if(ans != -1) return ans;
                ans = horizontal(currentPosition);
                    if(ans != -1) return ans;
            } else if(currentPosition.direction == Direction.HORIZONTAL){
                int ans = vertical(currentPosition);
                if(ans != -1) return ans;
            } else {
                int ans = horizontal(currentPosition);
                if(ans != -1) return ans;
            }
        }

        return -1;
    }

    private static int vertical(Position position){
        int currentValue = position.value;
        //Top
        for(int index = position.xCoordinate-1 ; index >=0 ; index--){
            if(grid[index].charAt(position.yCoordinate) == 'X' || arr[index][position.yCoordinate] > 0)
                break;
            if(index == row && position.yCoordinate == col){
                return currentValue + 1;
            }

            arr[index][position.yCoordinate] = currentValue+1;
            queue.add(new Position(currentValue + 1, index, position.yCoordinate, Direction.VERTICAL));
        }

        //Bottom
        for(int index=position.xCoordinate+1 ; index<arr.length ; index++){
            if(grid[index].charAt(position.yCoordinate) == 'X' || arr[index][position.yCoordinate] > 0)
                break;
            if(index == row && position.yCoordinate == col){
                return currentValue + 1;
            }

            arr[index][position.yCoordinate] = currentValue+1;
            queue.add(new Position(currentValue + 1, index, position.yCoordinate, Direction.VERTICAL));
        }

        return -1;
    }

    private static int horizontal(Position position){
        int currentValue = position.value;
        //Left
        for(int index=position.yCoordinate-1 ; index>=0 ; index--){
            if(grid[position.xCoordinate].charAt(index) == 'X' || arr[position.xCoordinate][index] > 0)
                break;
            if(position.xCoordinate == row && index == col){
                return currentValue + 1;
            }

            arr[position.xCoordinate][index] = currentValue+1;
            queue.add(new Position(currentValue+1, position.xCoordinate, index, Direction.HORIZONTAL));
        }

        //Right
        for(int index=position.yCoordinate+1 ; index<arr[0].length ; index++){
            if(grid[position.xCoordinate].charAt(index) == 'X' || arr[position.xCoordinate][index] > 0)
                break;
            if(position.xCoordinate == row && index == col){
                return currentValue + 1;
            }

            arr[position.xCoordinate][index] = currentValue+1;
            queue.add(new Position(currentValue+1, position.xCoordinate, index, Direction.HORIZONTAL));
        }

        return -1;
    }

    private static void print(int arr[][]){
        for(int row=0 ; row<arr.length ; System.out.println(""), row++){
            for(int col=0 ; col<arr[row].length ; col++){
                System.out.print(arr[row][col] + " ");
            }
        }

        System.out.println("\n\nPointer : " + arr[row][col]);
    }
}

enum Direction{
    HORIZONTAL, VERTICAL, START;
}

class Position{
    int xCoordinate;
    int yCoordinate;
    int value;
    Direction direction;

    Position(){}
    Position(int value, int xCoordinate, int yCoordinate, Direction direction){
        this.value = value;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.direction = direction;
    }
}