package com.ds.tree;

public class InvertTree {

	class Node {
		Node left;
		Node right;
		int data;

		public Node(int data) {
			super();
			this.data = data;
		}

	}

	static Node invert(Node node) {
		if (node == null)
			return null;
		if (node.left == null && node.right == null) {
			return node;
		}
		Node result = node(node.data);
		result.left = invert(node.right);
		result.right = invert(node.left);
		return result;
	}

	public static  void print(Node node, int level) {
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

	public static void main(String[] args) {
		InvertTree tree = new InvertTree();
		Node root = node(1);
		root.left = node(2);
		root.right = node(3);
		root.left.left = node(4);
		root.left.right = node(5);
		root.right.left = node(6);
		root.right.right = node(7);
		root.left.left.left = node(8);
		root.left.left.right = node(9);

		printingTree(root);
		System.out.println("After Inverting");
		Node result = invert(root);
		printingTree(result);
	}

	private static void printingTree(Node result) {
		System.out.println("level 1");
		print(result, 1);
		System.out.println("level 2");
		print(result, 2);
		System.out.println("level 3");
		print(result, 3);
		System.out.println("level 4");
		print(result, 4);
	}

	private static Node node(int data) {
		InvertTree tree = new InvertTree();
		return tree.new Node(data);
	}
}
