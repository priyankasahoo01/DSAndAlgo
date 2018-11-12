package com.problem.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class KthSmallestBST {
    class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        KthSmallestBST bst = new KthSmallestBST();
        Node root = node(4);
        root.left = node(2);
        root.left.left = node(1);
        root.left.right = node(3);
        root.right = node(7);
        
        List<Integer> op1 = new ArrayList<>();
        AtomicInteger i = new AtomicInteger(0);

        Integer ip = bst.inorder(root, i,4);
        System.out.println(" op --> "+ip);
        
        
        
        
//        int op2 = bst.get(root, op1, 3);
//        System.out.println("op --> "+op2);
//        bst.inorder(node, op1,3);
//        System.out.println("op1 ---> "+op1.get(2));
//        Integer op = bst.inorder(node, 3);
//        System.out.println("op --> " + op);

    }

    private static Node node(int data) {
        KthSmallestBST bst = new KthSmallestBST();
        Node node = bst.new Node(data);
        return node;
    }
    
    
    public Integer get(Node root, List<Integer> li , int k ) {
        if(root == null) {return null;}
        
        get(root.left, li, k);
        li.add(root.data);
        if(li.size() == k) {return root.data;}
        
        get(root.right, li, k);
        return null;
    }
    public Integer inorder(Node root, AtomicInteger i, int k) {
//        if(op.size() == k) {return;}
        if(root == null) {return null;}
//        if(root.left!=null) {
//            Integer val = inorder(root.left,i,k);
//            if(val!=null) {return val;}
//        }
        Integer lt = inorder(root.left,i,k);
        if(lt!=null) {
            return lt;
        }
//        Integer val = inorder(root.left,i,k);
//        if(val!=null) {return val;}
        if(i.incrementAndGet()==k) {
            return root.data;}
        
//        return inorder(root.right,i,k);
        return inorder(root.right,i,k);
//        k++;
    }

    // 2,3,4,5
    public Integer inorder(Node root, int k) {
        if (root == null) {
            return null;
        }
        if (k == 0) {
            return root.data;
        }
        Integer d = inorder(root.left, k - 1);

        Integer d1 = inorder(root.right, k - 1);
        return d == null ? d1 : d;

    }
/*
    public Integer smallest(Node root, int k) {
        if (root == null) {
            return null;
        }
        Node node = root;
        for (int i = 0; i < k && node != null; i++) {
            node = node.left;
        } // less than k nodes if(i!=k) {}

    }*/

}
