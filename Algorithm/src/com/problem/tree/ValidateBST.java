package com.problem.tree;

public class ValidateBST {
    public static void main(String[] args) {
        Node root = node(20);
        root.right = node(12);
        System.out.print("inoreder --> ");
        inorder(root);
        System.out.println();
//        System.out.println(validateBST(root));
        System.out.println(validate(root));
        System.out.println("------");

        Node root1 = node(90);
        root1.left = node(12);
        root1.left.left = node(11);
        root1.right = node(121);
        System.out.print("inoreder --> ");
        inorder(root1);
        System.out.println();
//        System.out.println(validateBST(root1));
        System.out.println(validate(root1));
        System.out.println("------");
        // should be false.. as 25>20, should be on right side
        Node root2 = node(20);
        root.right = node(30);
        root2.left = node(10);
        root2.left.right = node(25);
        System.out.print("inoreder --> ");
        inorder(root2);
        System.out.println();
//        System.out.println(validateBST(root2));
        System.out.println(validate(root2));
        System.out.println("------");
    }

    public static boolean validate(Node root) {
        return validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean validateBST(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val < min || root.val > max) {
            return false;
        }
        boolean lt = validateBST(root.left, min, root.val);
        boolean rt = validateBST(root.right, root.val, max);
        return (lt && rt);
    }

    private static Node node(int data) {
        ValidateBST bst = new ValidateBST();
        Node root = bst.new Node(data);
        return root;
    }

    public static void inorder(Node node) {
        if(node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.val+" ");
        inorder(node.right);
    }
    // incorrrect
    public static boolean validateBST(Node node) {
        if (node == null) {
            return true;
        }
        if (isLeafNode(node)) {
            return true;
        }
        if (node.left != null) {
            if (node.left.val > node.val) {
                return false;
            }
        }

        if (node.right != null) {
            if (node.right.val < node.val) {
                return false;
            }
        }
        return validateBST(node.left) && validateBST(node.right);

    }

    private static boolean isLeafNode(Node root) {
        if (root != null && root.left == null && root.right == null) {
            return true;
        }
        return false;
    }

    class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            super();
            this.val = val;
        }

    }

}
