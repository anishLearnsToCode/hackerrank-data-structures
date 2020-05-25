package trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Letter {
    private HashMap<Character, Letter> map;
    private boolean isWord;
    private char letter;
    private int words = 0;

    Letter(char letter){
        map = new HashMap<>();
        isWord = false;
        this.letter = letter;
    }
    Letter(boolean isWord, char letter){
        this.isWord = isWord;
        map = new HashMap<>();
        this.letter = letter;
    }

    public boolean isWord(){
        return isWord;
    }
    public char value(){
        return letter;
    }
    public int getWords(){
        return words;
    }

    public void addWord(String word){
        words++;

        if(word.length() == 0){
            this.isWord = true;
            return;
        }

        if(map.containsKey(word.charAt(0)))
            map.get(word.charAt(0)).addWord(word.substring(1));
        else {
            map.put(word.charAt(0), new Letter(word.charAt(0)));
            map.get(word.charAt(0)).addWord(word.substring(1));
        }
    }
    public void print(String name){
        name = name + letter;
        if(isWord)
            System.out.println(name);

        for(Map.Entry<Character, Letter> letterEntry : map.entrySet()){
            letterEntry.getValue().print(name);
        }
    }
    public int numberOfClosestEntries(String word){
        if(word.length() == 0)
            return words;

        if(map.containsKey(word.charAt(0)))
            return map.get(word.charAt(0)).numberOfClosestEntries(word.substring(1));

        return 0;
    }
    public boolean containsPrefix(String word){
        if(word.length() == 0 || words <= 1)
            return false;

        if(isWord)
            return true;

        if(map.containsKey(word.charAt(0)))
            return map.get(word.charAt(0)).containsPrefix(word.substring(1));

        return false;
    }
}


class Node{
    private HashMap<Character, Letter> map;

    Node(){
        map = new HashMap<>();
    }

    public boolean containsLetter(char letter){
        return map.containsKey(letter);
    }
    public void add(char letter){
        map.put(letter, new Letter(letter));
    }
    public void addWord(String word){
        map.get(word.charAt(0)).addWord(word.substring(1));
    }
    public void print(){
        for(Map.Entry<Character, Letter> letterEntry : map.entrySet()){
            letterEntry.getValue().print("");
        }
    }
    public int numberOfClosestEntries(String word){
        if(word.length() == 0)
            return map.size();

        if(map.containsKey(word.charAt(0)))
            return map.get(word.charAt(0)).numberOfClosestEntries(word.substring(1));

        return 0;
    }
    public boolean containsPrefix(String word){
        if(word.length() == 0)
            return false;

        if(map.containsKey(word.charAt(0))){
            Letter firstLetter = map.get(word.charAt(0));
            if(firstLetter.getWords() > 1)
                return firstLetter.isWord() || firstLetter.containsPrefix(word.substring(1));
            return false;
        }

        return false;
    }
}


class Trie {
    private Node start;

    Trie(){
        start = new Node();
    }

    public void addWord(String string){
        if(string.length() == 0)
            return;

        if(start.containsLetter(string.charAt(0))){
            start.addWord(string);
        } else {
            start.add(string.charAt(0));
            start.addWord(string);
        }
    }
    public void print(){
        start.print();
    }
    public int numberOfClosestEntries(String word){
        return start.numberOfClosestEntries(word);
    }
    public boolean containsPrefix(String word){
        return start.containsPrefix(word);
    }
}

public class Contacts {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int queries = in.nextInt();
        performQueries(queries);
    }

    private static void performQueries(int queries){
        Trie contacts = new Trie();

        while (queries-- > 0){
            String operation = in.next();
            String name = in.next();

            if(operation.charAt(0) == 'a'){
                contacts.addWord(name);
            } else {
                System.out.println(contacts.numberOfClosestEntries(name));
            }
        }

        contacts.print();
    }
}
