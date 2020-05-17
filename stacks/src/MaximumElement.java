import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class MaximumElement {
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int queries = s.nextInt();
        performQuery(queries);
    }

    private static void performQuery(int queries){
        Stack<Long> stack = new Stack<>();
        HashMap<Integer, Long> towerValue = new HashMap<>();
        towerValue.put(0, 0L);

        while (queries-- > 0){

            int queryType = s.nextInt();

            if(queryType == 1){
                long data = s.nextLong();
                stack.push(data);
                long previousMaxValue = towerValue.get(stack.size()-1);
                towerValue.put(stack.size(), Math.max(previousMaxValue, data));
            } else if(queryType == 2){
                stack.pop();
            } else {
                long ans = towerValue.get(stack.size());
                System.out.println(ans);
            }
        }
    }
}
