package com.ds.tree;

public class DeepestNode {
	class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            super();
            this.data = data;
        }

    }
	
	static Node deepestNode(Node root) {
		if(root == null) return null;
		if(root.left == null && root.right == null) return root;
		if(root.left != null)
			return deepestNode(root.left);
		return deepestNode(root.right);
	}

	public static void main(String[] args) {
		Node root = node(1);
        root.left = node(2);
        root.right = node(3);
        root.left.left = node(4);
        root.left.right = node(5);
        root.right.left = node(6);
        root.right.right = node(7);
        root.left.left.left = node(8);
        root.left.left.right = node(9);
        Node result = deepestNode(root);
        System.out.println("op --> "+ result == null ? "null" : result.data);

	}
	
	private static Node node(int data) {
		DeepestNode tree = new DeepestNode();
        return tree.new Node(data);
    }

}
