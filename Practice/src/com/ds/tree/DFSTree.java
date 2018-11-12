package com.ds.tree;

import java.util.ArrayList;
import java.util.List;

import com.ds.practice.Stack;
import com.ds.tree.Tree.Node;

public class DFSTree {
    private Stack<Tree.Node> stack;

    public DFSTree(int size) {
        stack = new Stack<>(size);
    }

    public void traverse(Tree tree) throws Exception {
        if (tree == null || tree.root == null) {
            return;
        }
        Node root = tree.root;
        stack.push(root);
        List<Node> visitedNodes = new ArrayList<>();
        visitedNodes.add(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.data);
            if (node.left != null) {
                visitedNodes.add(node.left);
            }
            if (node.right != null) {
                visitedNodes.add(node.right);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        
        DFSTree dfs = new DFSTree(9);
        Tree tree = new Tree();

        Tree.Node root = node(1);
        tree.root = root;
        root.left = node(2);
        root.right = node(3);
        root.left.left = node(4);
        root.left.right = node(5);
        root.right.left = node(6);
        root.right.right = node(7);
        root.left.left.left = node(8);
        root.left.left.right = node(9);
        dfs.traverse(tree);
    }

    private static Node node(int i) {
        Tree tree = new Tree();
        return tree.new Node(i);
    }

}
