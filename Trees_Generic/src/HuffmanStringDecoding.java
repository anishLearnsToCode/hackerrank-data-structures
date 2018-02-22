import java.util.Scanner;

public class HuffmanStringDecoding {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Tree<Character> root = new Tree<>();
        root.input();
        root.print();

        String binaryCode = s.next();
        String huffmanStr = huffmanString(root, binaryCode);
        System.out.println(huffmanStr);
    }

    private static String huffmanString(Tree<Character> root, String binaryCode){
        String ans = "";
        Tree<Character> temp = root;

        for(int i=0 ; i<binaryCode.length() ; i++){
            if(binaryCode.charAt(i) == '0') {
                temp = temp.left;
                if(temp.data != '\0') {
                    ans += temp.data;
                    temp = root;
                }
            } else {
                temp = temp.right;
                if(temp.data != '\0'){
                    ans += temp.data;
                    temp = root;
                }
            }
        }

        return ans;
    }
}
