package com.problem.tree;

public class CountOfDeepestNodes {
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
    
    /* The constructed Tree would be 
    30 
    / \ 
   20  40 
    / \  \ 
    10 25 50 
     */
    public static void main(String[] args) {
        CountOfDeepestNodes obj = new CountOfDeepestNodes();
        
        Node node = obj.root;
        node = nodes(30);
        node.left = nodes(20);
        node.right = nodes(40);
        node.left.left = nodes(10);
        node.left.right = nodes(25);
        node.right.right = nodes(50);
        
        int count = countTotaltNodes(node);
        System.out.println("Total count of nodes--> "+count);
        levelWisePrinting(node, 2);
        int countOfLeafNodes = countOfLeafNodes(node);
        System.out.println("Total number of leaf npdes --> "+countOfLeafNodes);
        count = getMaxLevel(node,1);
        System.out.println("Total count of deepest nodes --> "+count);
        
        
        node.left.left.left = nodes(80);
        count = getMaxLevel(node,1);
        System.out.println("Total count of deepest nodes --> "+count);
        
    }
    
    private static int countOfLeafNodes(Node node) {
        if(node == null) {
            return 0;
        }
        if(isLeaf(node)) {
            return 1;
        }
        return countOfLeafNodes(node.left)+countOfLeafNodes(node.right);
    }
    
    private static int getMaxLevel(Node node, int level) {
        if(node == null) {
            return 0;
        }
        if(isLeaf(node)) {
            return level;
        }
        int leftCount = getMaxLevel(node.left, level+1);
        int rightCount = getMaxLevel(node.right, level+1);
        int maxCount = Math.max(leftCount, rightCount);
        return maxCount;
    }

    private static void levelWisePrinting(Node node, int level) {
        if(level == 0) {
            return;
        }
        if(level == 1) {
            System.out.println(node.data);
            return;
        }
        levelWisePrinting(node.left, level-1);
        levelWisePrinting(node.right, level-1);
    }
    
    private static boolean isLeaf(Node node) {
        if(node!=null && node.left==null && node.right==null) {
            return true;
        }
        return false;
    }
    
    private static int countTotaltNodes(Node node) {
        if(node == null) {
            return 0;
        }
        if(isLeaf(node)) {
            return 1;
        }
        return 1+countTotaltNodes(node.left) +countTotaltNodes(node.right);
    }
    
    public static Node nodes(int data) {
        CountOfDeepestNodes obj = new CountOfDeepestNodes();
        return createNode(data, obj);
    }

    private static Node createNode(int data, CountOfDeepestNodes obj) {
        Node node = obj.new Node(data);
        return node;
    }

}
