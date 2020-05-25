import java.util.Scanner;

public class HuffmanStringDecoding {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        CustomTree<Character> root = new CustomTree<>();
        root.input();
        root.print();

        String binaryCode = s.next();
        String huffmanStr = huffmanString(root, binaryCode);
        System.out.println(huffmanStr);
    }

    private static String huffmanString(CustomTree<Character> root, String binaryCode){
        String ans = "";
        CustomTree<Character> temp = root;

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
