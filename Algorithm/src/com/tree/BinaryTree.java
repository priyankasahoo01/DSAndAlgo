package com.tree;

public class BinaryTree<T> implements Tree<T>{
    Node root;
    class Node{
        T data;
        Node left;
        Node right;
        Node(T data){
            this.data = data;
        }
    }
    
    class Height{
        int height;
    }
    //|ht(left)-ht(right)| <=1
    public boolean isBalanced(Node node, Height ht) {
        
        if(node == null) {
            ht=new Height();
            return true;
        }
        Height ltHeight = new Height();
        Height rtHeight = new Height();
        
        ht.height = 1 + ((ltHeight.height>rtHeight.height)?ltHeight.height:rtHeight.height);
        boolean ltBal = isBalanced(node.left, ltHeight);
        boolean rtBal = isBalanced(node.left, rtHeight);
        
        if(ltHeight.height -rtHeight.height >1 || rtHeight.height - ltHeight.height > 1) {
            return false;
        }
        return ltBal && rtBal;
        
    }

    @Override
    public void add(T data) {
        Node node = new Node(data);
        if(root == null) {
            root = node;
        }else {
           // while()
        }
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public int level() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub
        
    }

}
