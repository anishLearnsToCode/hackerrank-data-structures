import java.util.Scanner;

public class NoPrefixSet {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Trie prefixSet = new Trie();
        int n = in.nextInt();
        boolean flag = true;

        while (n-- > 0){
            String word = in.next();
            prefixSet.addWord(word);
            if(prefixSet.containsPrefix(word)){
                System.out.println("BAD SET");
                flag = false;
                System.out.println(word);
                break;
            }
        }

        if(flag)
            System.out.println("GOOD SET");
    }
}
