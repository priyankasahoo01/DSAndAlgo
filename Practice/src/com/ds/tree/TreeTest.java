package com.ds.tree;

import com.ds.tree.Tree.Node;
import com.ds.tree.Tree.TraverseType;

public class TreeTest {
    public static void main(String[] args) {

        Tree tree = new Tree();
        tree.add(3);
        System.out.println("depth is : "+tree.depthOfTree());
        tree.add(1);
        System.out.println("depth is : "+tree.depthOfTree());
        tree.add(2);
        System.out.println("depth is : "+tree.depthOfTree());
        tree.add(4);
        System.out.println("depth is : "+tree.depthOfTree());
        tree.add(5);
        System.out.println("depth is : "+tree.depthOfTree());
        tree.add(23);
        System.out.println("depth is : "+tree.depthOfTree());
        tree.add(0);
        System.out.println("depth is : "+tree.depthOfTree());
        tree.traverse(TraverseType.PREORDER);
        tree.traverse(TraverseType.INORDER);
        tree.traverse(TraverseType.POSTORDER);
        BFSTree bfs = new BFSTree(5);
        try {
            System.out.println("\nbfs traverse");
            
            bfs.traverse(tree);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        DFSTree dfs = new DFSTree(5);
        System.out.println("\nDFS tree");
        try {
            dfs.traverse(tree);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    
        System.out.println("\nparallel\n");
        Tree t1 = new Tree();
        Node node = t1.new Node(2);
        node.left = t1.new Node(3);
        node.right = t1.new Node(4);
        node.left.left = t1.new Node(5);
        node.left.right = t1.new Node(6);
        
        node.right.left = t1.new Node(7);
        node.right.right = t1.new Node(8);
//        
        t1.root = node;
        t1.traverse(TraverseType.PARALLEL);
        
        
        System.out.println("serialize");
        String ser = t1.serialize(node);
        System.out.println(ser);
        
        
        
        
//        polymorph(0);
//        polymorph(null);
    }
    
    public static void polymorph(int i) {
        System.out.println("int");
    }
//    public static void polymorph(Integer i) {
//        System.out.println("integer");
//    }
//    public static void polymorph(Date d) {
//        System.out.println("date");
//    }
    
    public static void polymorph(Object o) {
        System.out.println("object");
    }
    
    public static void polymorph(String s) {
        System.out.println("String");
    }
    
    

}
