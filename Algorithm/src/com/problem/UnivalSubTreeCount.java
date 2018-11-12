package com.problem;

public class UnivalSubTreeCount {
    class Node {
        Node left;
        Node right;
        int data;

        public Node(int val) {
            data = val;
        }
    }

    Node root;

    public static void main(String[] args) {
        Node node = node(0);
        node.left = node(1);
        node.right = node(0);
        node.right.left = node(1);
        node.right.right = node(0);
        
        node.right.left.left = node(1);
        node.right.left.right = node(1);
        System.out.println(getCount(node));

    }

    private static Node node(int val) {
        UnivalSubTreeCount sub = new UnivalSubTreeCount();
        Node node = sub.new Node(val);
        return node;
    }

    public static int getCount(Node node) {
        if (node == null) {
            return 0;
        }
        if (isLeaf(node)) {
            return 1;
        }
        if (node.left != null && node.right != null) {
            if (node.left.data == node.right.data) {
                return 1 + getCount(node.left) + getCount(node.right);
            }
            return getCount(node.left) + getCount(node.right);
        }
        return 0;
    }

    private static boolean isLeaf(Node node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                return true;
            }
        }
        return false;
    }

}
