package com.ds.tree;

import java.util.ArrayList;
import java.util.List;

import com.ds.tree.InvertTree.Node;

public class RootToLeafSum {
	class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;

		public TreeNode(int data) {
			super();
			this.val = data;
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		TreeNode root = node(1);
//		root.left = node(2);
//		root.right = node(3);
//		root.left.left = node(4);
//		root.left.right = node(5);
//		root.right.left = node(6);
//		root.right.right = node(7);
//		root.left.left.left = node(8);
//		root.left.left.right = node(9);
		
		TreeNode root = node(5);
		root.left = node(4);
		root.right = node(8);
		root.left.left = node(11);
//		root.left.right = node(5);
		root.right.left = node(13);
		root.right.right = node(4);
		root.left.left.left = node(7);
		root.left.left.right = node(2);
		
		root.right.right.left = node(5);
		root.right.right.right = node(1);
		RootToLeafSum tree = new RootToLeafSum();
		int[][] op = tree.pathSum(root, 22);
		System.out.println("op");
		for(int i = 0 ; i < op.length ; i++) {
			System.out.print("[");
			for(int j = 0 ; j < op[i].length ; j++) {
				System.out.print(op[i][j] +" , ");
			}
			System.out.println("]\n");
		}
	}
	private static TreeNode node(int data) {
		RootToLeafSum tree = new RootToLeafSum();
		return tree.new TreeNode(data);
	}
	public int[][] pathSum(TreeNode node, int sum) {
        List<List<Integer>> op = new ArrayList<>();
        path(node, sum, new ArrayList<Integer>(), op);
        int [][]result = new int[op.size()][];
        for(int i = 0 ; i < op.size(); i++){
            result[i] = new int[op.get(i).size()];
            for(int j = 0 ; j < op.get(i).size() ; j++){
                result[i][j] = op.get(i).get(j);
            }
        }
        return result;
    }
    
    
    boolean leaf(TreeNode node){
        return node.left == null && node.right == null;
    }
    List<Integer> copy(List<Integer> list){
        if( list == null ) return null;
        List<Integer> op = new ArrayList<>();
        for(Integer i : list){
            op.add(i);
        }
        return op;
    }
    
    public void path(TreeNode node, int sum, List<Integer> path, List<List<Integer>> result){
        //if(sum == 0){ if(leaf(node))return path; else return null;}
        //if(sum < 0 || sum < node.val){ return null;}
        if(node == null) return;
        if(leaf(node)){
            if(sum-node.val == 0){
                path.add(node.val);
                result.add(path);
                return;
            }else{
                return;
            }
        }
        
        path.add(node.val);
        
        if(node.left != null){
            List<Integer> copylist = copy(path);
            path(node.left, sum - node.val , copylist, result);
        }
        
        if(node.right != null){
            List<Integer> copylist = copy(path);
            path(node.right, sum - node.val , copylist, result);
        }
        
      
        
    }

	

}
