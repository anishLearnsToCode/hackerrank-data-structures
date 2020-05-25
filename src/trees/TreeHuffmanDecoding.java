package trees;

import java.util.Scanner;

public class TreeHuffmanDecoding {
    public static void main(String[] args) {
        Tree<Character> root = new Tree<>();
        root.input();
        root.print();

        Scanner s = new Scanner(System.in);
        String binaryString = s.next();

        String huffmanStr = huffmanString(root, binaryString);
        System.out.println(huffmanStr);
    }

    private static String huffmanString(Tree root, String binaryStr){
        return "";
    }
}
