package ua.com.alevel.secondlevel;

import ua.com.alevel.TaskRunner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BinaryTree implements TaskRunner {

    private TreeNode root;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left_Depth = maxDepth(root.left);
        int right_Depth = maxDepth(root.right);
        int bigger = Math.max(left_Depth, right_Depth);
        return bigger + 1;
    }

    private TreeNode addRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }
        if (value < current.val) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.val) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }
        return current;
    }

    private void add(int value) {
        root = addRecursive(root, value);
    }

    private void addNodsFromString(String str) {
        String[] array = str.split(" ");
        for (String s : array) {
            add(Integer.parseInt(s));
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the binary tree through spaces:");
                String string = bufferedReader.readLine();
                addNodsFromString(string);
                System.out.println("Max depth of binary tree = " + maxDepth(root));
                break;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
