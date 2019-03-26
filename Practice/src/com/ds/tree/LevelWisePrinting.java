package com.ds.tree;

import com.ds.tree.Tree.Node;


public class LevelWisePrinting {
	class Num {int num, power;}
    public void print(Node node, int level) {
        if (level == 0) {
            return;
        }
        if (level == 1) {
            System.out.println(node.data + " ");
            return;
        }
        if (node.left != null) {
            print(node.left, level - 1);
        }
        if (node.right != null) {
            print(node.right, level - 1);
        }
    }

    public void testPrint() {
        Node root = node(1);
        root.left = node(2);
        root.right = node(3);
        root.left.left = node(4);
        root.left.right = node(5);
        root.right.left = node(6);
        root.right.right = node(7);
        root.left.left.left = node(8);
        root.left.left.right = node(9);
        System.out.println("level 1");
        print(root, 1);
        System.out.println("level 2");
        print(root, 2);
        System.out.println("level 3");
        print(root, 3);
        System.out.println("level 4");
        print(root, 4);
    }

    public static void main(String[] args) {
        LevelWisePrinting levelWisePrinting = new LevelWisePrinting();
        levelWisePrinting.testPrint();
    }

    private Node node(int data) {
        Tree tree = new Tree();
        return tree.new Node(data);
    }

}
