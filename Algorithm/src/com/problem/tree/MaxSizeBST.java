package com.problem.tree;

public class MaxSizeBST {
//	class Node{
//		Node left;
//		Node right;
//		int val;
//		
//		Node(int data){this.val = data;}
//	}
	
	public static void main(String[] args) {
		Node node1 = new Node(1);
        Node node2 = node1.left(2);
        Node node4 = node2.left(4);
        Node node5 = node2.right(5);
        Node node3 = node1.right(3);
        Node node6 = node3.left(6);
        Node node8 = node6.left(8);
        Node node7 = node3.right(7);
        Node node9 = node5.left(9);
        Node node10 = node5.right(10);
        Node node11 = node10.right(11);
        
        MaxSizeBST bst = new MaxSizeBST();
//        int op = bst.findMaxSizeBST(node1);
//        System.out.println("output --> "+op);
        
        
        Node n1 = new Node(23);
        n1.right = new Node(26);
        n1.left = new Node(20);
        n1.left.right = new Node(22);
        n1.right = new Node(26);
        n1.left.left= new Node(17);
        n1.left.left.left = new Node(10);
        n1.left.left.right = new Node(19);
        int op1 = bst.findMaxSizeBST(n1);
        System.out.println("op --> "+op1);
		
	}

	int findMaxSizeBST(Node node) {
		Result max = find1(node);
//		if(max.isBST) {return max.size;}
		return max.size;
	}
	
	public Result find1(Node node) {
		if(node == null) {return new Result(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);}
		
		if(isLeaf(node)) {
			return new Result(true, 1, node.val, node.val);
		}
		
		Result left = find1(node.left);
		Result right = find1(node.right);
		if(left.isBST && right.isBST) {
			if(node.val > left.max && node.val<right.min) {
				return new Result(true, left.size+right.size+1,left.max, right.min);
			}else {
				if(left.size>right.size) {
					return new Result(false, left.size,left.min, left.max);
				}
				return new Result(false, right.size,right.min, right.max);
			}
			
		}else {
			if(left.isBST) {
				return new Result(false, left.size,left.min, left.max);
			}else {
				return new Result(false, right.size,right.min, right.max);
			}
		}
		//Result result = new Result(left.isBST&&right.isBST, )
	}

	boolean isLeaf(Node node) {
		if (node != null && node.left == null && node.right == null) {
			return true;
		}
		return false;
	}

	class Result {
		int size;
		int min;
		int max;
		boolean isBST;
		Result(boolean isBST, int size, int min, int max){
			this.size = size;
			this.min=min;
			this.max=max;
			this.isBST=isBST;
		}
	}

	/*Result find(Node node) {
		if (node == null) {
			return null;
		}
		if (isLeaf(node)) {
			return new Result(1, node.val, node.val, true);
		}
		
		Result result = new Result(1, node.val,node.val,true);

		if(node.left !=null) {
			Result left = find(node.left);
			if(left.isBST && node.val > left.max) {
				new Result(1+left.size, left.min, node.val, true);
			}else if(left.isBST){
//				new Result(1+left.size, left.min, , false);
				result = left;
			}
		}

		
		if(node.right!=null) {
			Result right = find(node.right);
			int size = result.size
			if(right.isBST && node.val < right.min ) {
				if(result.isBST) {
					
				}
			}
		}
		
		
		
		// if(left.isBST == false && right.isBST == false){return new
		// Result(0,0,0,false);}
		if (left!=null && right!=null && left.isBST && right.isBST) {
			if (node.val > left.max && node.val < right.min) {
				return new Result(Math.max(left.size, right.size) + 1, left.min, right.max, true);
			} else {
				return new Result(Math.max(left.size, right.size), 0, 0, false);
			}
		}
		
		
		if (left!=null && left.isBST) {
			return left;
		}
		return right;

	}*/

}
