package com.problem.tree;

public class InorderSuccessor {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = node1.left(2);
        Node node4 = node2.left(4);
        Node node5 = node2.right(5);
        Node node3 = node1.right(3);
        Node node6 = node3.left(6);
        Node node8 = node6.left(8);
        Node node7 = node3.right(7);
        
        Node node9 = node5.left(9);
        Node node10 = node5.right(10);
        Node node11 = node10.right(11);
        System.out.print("inoreder --> ");
        inorder(node1);
        test(node1);
        test(node2);
        
        test(node3);
        test(node4);
        test(node5);
        test(node6);
        test(node7);
        test(node8);
        test(node9);
        test(node10);
        test(node11);
    }

    private static void test(Node node1) {
        Node op = getInorderSuccessor(node1);
        System.out.println("successor of "+node1.val+" --> "+(op!=null?op.val:op));
    }

    public static Node getInorderSuccessor(Node node) {
        if (node == null) {
            return node;
        }
        // if right child exists, then get leftmost node of RT child subtree
        if (node.right != null) {
            return getLeftMost(node.right);
        }
        // if no RT child exists

        // if current is right child of parent, means its parent is already covered,
        // keep moving upwards
        Node parent = node.parent;
//        Node current = node;
        while (parent != null) {
            // if current is left child of parent
            if (parent.left == node) {
                // it means the parent is not yet covered, return parent
                return parent;
            }
            // otherwise, keep moving upwards
            node = parent;
            parent = parent.parent;
            
        }
        return parent;
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return null;
        }
        Node left = node.left;
        while (left != null) {
            if(left.left == null) {
                return left;
            }
            left = left.left;
        }
        return node;
    }

    public static void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

}
