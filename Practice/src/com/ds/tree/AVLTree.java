package com.ds.tree;

public class AVLTree {
    private static final String RIGHT = "RIGHT";
    private static final String LEFT = "LEFT";
    Node root;

    public void insert(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        while (current != null) {
            if (data < current.data) {
                if(current.left == null) {
                    addNode(current, node, LEFT);
                    break;
                }
                current = current.left;
            }
            if (data > current.data) {
                if(current.right == null) {
                    addNode(current, node, RIGHT);
                    break;
                }
                current = current.right;
            }
        }
        
    }

    private void addNode(Node current, Node node, String dir) {
        if(LEFT.equals(dir)) {
            current.left = node;
            
            
        }else {
            current.right = node;
        }
        
        
    }

    class Node {
        Node left;
        Node right;
        int data;
        int lHeight;
        int rHeight;

        Node(int val) {
            this.data = val;
        }
    }

}
