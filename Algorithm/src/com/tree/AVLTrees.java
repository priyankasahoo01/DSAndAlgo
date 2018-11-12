package com.tree;

public class AVLTrees {

    public static void main(String[] args) {
        AVLTrees tree = new AVLTrees(); 

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 10); 
        tree.root = tree.insert(tree.root, 20); 
        tree.root = tree.insert(tree.root, 30); 
        tree.root = tree.insert(tree.root, 40); 
        tree.root = tree.insert(tree.root, 50); 
        tree.root = tree.insert(tree.root, 25); 

        /* The constructed AVL Tree would be 
            30 
            / \ 
        20 40 
        / \  \ 
        10 25 50 
        */
        System.out.println("Preorder traversal" + 
                        " of constructed tree is : "); 
        tree.preOrder(tree.root); 

    }

    private void preOrder(Node root) {
        if(root == null) {
            System.out.print("");
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    class Node {
        Node left;
        Node right;
        int data;
        int height;

        Node(int data) {
            this.data = data;
        }
    }

    Node root;

    int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
            return node;
        }
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }
        node.height = height(node);

        int balance = balance(node);

        // ll
        if (balance > 1 && data < node.left.data) {
            return right(node.left);
        }
        // lr
        if (balance > 1 && data > node.left.data) {
            node.left = left(node.left);
            return right(node);
        }

        // rr
        if (balance < -1 && data > node.right.data) {
            return left(node.right);
        }
        // rl
        if (balance < -1 && data < node.right.data) {
            node.right = right(node.right);
            return left(node.right);
        }

        return node;

    }

    private Node left(Node node) {
        Node curr = node;
        Node rt = curr.right;
        Node zLt = rt.left;
        rt.left=curr;
        curr.right = zLt;
        return curr;
    }

    private Node right(Node node) {
        Node curr = node;
        Node lt = curr.left;
        Node zRt = lt.right;
        lt.right = curr;
        curr = zRt;
        return lt;
    }

    private int balance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

}
