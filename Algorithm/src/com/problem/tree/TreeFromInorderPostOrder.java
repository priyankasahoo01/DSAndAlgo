package com.problem.tree;

public class TreeFromInorderPostOrder {

	public static void main(String[] args) {
		TreeFromInorderPostOrder sol = new TreeFromInorderPostOrder();
		int[] in = { 7, 5, 6, 2, 3, 1, 4  };
		int[] post = {  5, 6, 3, 1, 4, 2, 7 };
		TreeNode tree = sol.buildTree(in, post);
		sol.traverse(tree);
	}

	

	private void postorder(TreeNode node) {
		if (node == null)
			return;

		if (node.left == null && node.right == null) {
			System.out.print(node.val + " , ");
			return;
		}

		postorder(node.left);
		postorder(node.right);
		System.out.print(node.val + " , ");

	}

	private void preorder(TreeNode node) {
		if (node == null)
			return;

		if (node.left == null && node.right == null) {
			System.out.print(node.val + " , ");
			return;
		}

		System.out.print(node.val + " , ");
		preorder(node.left);
		preorder(node.right);

	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder.length != postorder.length)
			return null;
		if (inorder.length == 0)
			return null;
		Index index = new Index(postorder.length - 1);
		return build(inorder, postorder, index, 0, inorder.length - 1);
	}

	int getIndex(int[] arr, int num, int lo, int hi) {
		
		for(int i = lo ; i <= hi ; i++) {
			if(arr[i] == num) {
				return i;
			}
		}
		return -1;
	}

	// f(2, 0, 2) -> f(1, 2, 2) -->f(0, 3, 2) f(0, 2, 1 --> f(0, 0, 0)
	// 2,1,3 2, 3, 1
	private TreeNode build(int[] inorder, int[] postorder, Index index, int start, int end) {
		if (index.index < 0 || index.index > inorder.length - 1)
			return null;
		if(end < start) return null;
		if (start < 0 || start > inorder.length - 1)
			return null;
		if (end < 0 || end > inorder.length - 1)
			return null;

		int indexRoot = getIndex(inorder, postorder[index.index], start, end);// 2
		TreeNode root = new TreeNode(postorder[index.index]);
		index.index--;// 0

		root.right = build(inorder, postorder, index, indexRoot + 1, end);
		root.left = build(inorder, postorder, index, start, indexRoot - 1);
		return root;
	}
	
	public void traverse(TreeNode node) {
		System.out.print("\n PREORDER --> ");
		preorder(node);
		System.out.print("\n POSTORDER --> ");
		postorder(node);
	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
			left = null;
			right = null;
		}
	}

	class Index {
		int index;

		Index(int ind) {
			index = ind;
		}
	}

}
