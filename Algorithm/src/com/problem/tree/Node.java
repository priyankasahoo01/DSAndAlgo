package com.problem.tree;

public class Node {

    int val;
    Node left;
    Node right;
    Node parent;
    
    Node(int data){
        this.val = data;
    }
    public Node left( int data){
        left = new Node(data);
        left.parent=this;
        return left;
    }
    
    public Node right( int data){
        right = new Node(data);
        right.parent=this;
        return right;
    }
}
