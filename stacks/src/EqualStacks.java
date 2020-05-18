import java.security.cert.CertificateNotYetValidException;
import java.util.Scanner;
import java.util.Stack;

public class EqualStacks {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int heights[] = new int[3];
        for(int index = 0 ; index<3 ; index++){
            heights[index] = in.nextInt();
        }
        CylinderStack[] cylinderStacks = new CylinderStack[3];
        for(int i=0 ; i<3 ; cylinderStacks[i] = new CylinderStack(), i++);
        prepareStacks(cylinderStacks, heights);

        int maxHeight = maxPossibleHeight(cylinderStacks);
        System.out.println(maxHeight);
    }

    private static int maxPossibleHeight(CylinderStack[] cylinder){
        int index = maxHeightIndex(cylinder);
        while (!areEqual(cylinder)){
            cylinder[index].height -= cylinder[index].stack.pop();
            index = maxHeightIndex(cylinder);
        }

        return cylinder[0].height;
    }

    private static int maxHeightIndex(CylinderStack[] cylinder){
        int index1 = maxHeightIndex(cylinder[0], cylinder[1]);
        int index2 = maxHeightIndex(cylinder[1], cylinder[2]);

        if(cylinder[index1].height >= cylinder[index2+1].height)
            return index1;
        return index2+1;
    }

    private static int maxHeightIndex(CylinderStack cylinder1, CylinderStack cylinder2){
        if(cylinder1.height >= cylinder2.height)
            return 0;
        return 1;
    }

    private static boolean areEqual(CylinderStack[] cylinder){
        if(cylinder[0].height == cylinder[1].height)
            return cylinder[0].height == cylinder[2].height;

        return false;
    }

    private static void prepareStacks(CylinderStack[] stack, int heights[]){
        for(int i=0 ; i<3 ; i++){
            prepareStack(stack[i], heights[i]);
            stack[i].print();
        }
    }

    private static void prepareStack(CylinderStack stack, int height){
        int arr[] = new int[height];
        for(int index = 0 ; index<arr.length ; index++){
            arr[index] = in.nextInt();
        }
        for(int index = arr.length-1 ; index>=0 ; index--){
            stack.stack.push(arr[index]);
            stack.height += arr[index];
        }
    }
}

class CylinderStack {
    Stack<Integer> stack;
    int height;

    CylinderStack(){
        stack = new Stack<>();
        height = 0;
    }

    public void print() {
        for(int index = 0 ; index<stack.size() ; index++){
            System.out.print(stack.elementAt(index) + " ");
        }
    }
}
