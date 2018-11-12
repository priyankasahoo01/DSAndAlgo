package com.tree;

public class AVLTree {
    class Node {
        Node left;
        Node right;
        int data;
        int height;

        Node(int data) {
            this.data = data;
        }

        int findHeight(Node node) {
            if (node == null) {
                return 0;
            }
            return 1 + Math.max(findHeight(node.left), findHeight(node.right));
        }

        Node root;

        public int diffHeight(Node node){
//            if()
            return 0;
        }
        
        public void insert1(int data)

        public void insert(int data) {
            Node node = new Node(data);
            if (root == null) {
                root = node;
            }
            Node local = node;
            while (local != null) {
                if (data > local.data) {
                    if (local.right == null) {
                        local.right = node;
                        break;
                    }
                    local = local.right;
                }
                if (data < local.data) {
                    if (local.left == null) {
                        local.left = node;
                        break;
                    }
                    local = local.left;
                }
            }
            node.height = findHeight(node);
//            if (isbalanced(node)) {
//
//            }

        }

    }

}
