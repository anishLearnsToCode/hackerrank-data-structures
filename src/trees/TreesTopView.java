package trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class TreesTopView {
    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }

    public static void topView(Node root) {
        Map<Integer, Integer> view = new HashMap<>();
        generateView(view, root);
        printView(view);
        System.out.println();
    }

    private static void generateView(Map<Integer, Integer> view, Node root) {
        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(root, 0));

        while (!queue.isEmpty()) {
            Info element = queue.poll();
            if (element.node != null) {
                queue.add(new Info(element.node.left, element.level - 1));
                queue.add(new Info(element.node.right, element.level + 1));
                if (!view.containsKey(element.level)) {
                    view.put(element.level, element.node.data);
                }
            }
        }
    }

    private static void printView(Map<Integer, Integer> view) {
        for (int index = -500 ; index <= 500 ; index++) {
            if (view.containsKey(index)) {
                System.out.print(view.get(index) + " ");
            }
        }
    }

    private static class Info {
        Node node;
        int level;

        Info(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
