package com.ds.tree;

import com.ds.MyQueue;
import com.ds.tree.Tree.Node;

public class BFSTree {
    MyQueue<Node> queue;

    public BFSTree(int treeSize) {
        queue = new MyQueue<>(treeSize);
    }
    
    public void traverse(Tree tree) throws Exception {
        if(tree == null || tree.root == null) {
            return;
        }
        Node root = tree.root;
        
        queue.enqueue(root);
        while(!queue.isEmpty()) {
            Node ele = queue.dequeue();
            System.out.print(ele.data+" , ");
            if((ele.left!=null)) {queue.enqueue(ele.left);}
            
            if((ele.right!=null)) {queue.enqueue(ele.right);}
        }
    }

}
