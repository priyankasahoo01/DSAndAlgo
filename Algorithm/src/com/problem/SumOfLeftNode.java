package com.problem;

public class SumOfLeftNode {
    public static void main(String[] args) {
        TreeNode node = node(3);
        node.left=node(9);
        node.right=node(20);
        node.right.left=node(15);
        node.right.right=node(7);
        
        SumOfLeftNode s = new SumOfLeftNode();
        int op = s.sumOfLeftLeaves(node);
        System.out.println("op -> "+op);
    }
    private static TreeNode node(int data ) {
        SumOfLeftNode s = new SumOfLeftNode();
        
        return createNode(data, s);
    }
    private static TreeNode createNode(int data, SumOfLeftNode s) {
        TreeNode node = s.new TreeNode(data);
        return node;
    }
     class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    private boolean isLeaf(TreeNode root){
        if(root!=null){
            if(root.left==null && root.right==null){
                return true;
            }
        }
        return false;
    }
    public int sumOfLeftLeaves(TreeNode root) {
        return traverse(root,0,0);
    }
    private int traverse(TreeNode root, int count, int sum){
        
        if(count == 2 ){
            return sum;
        }
        if(root == null){
            return 0;
        }
        if(count<2) {
            
        }
        if(isLeaf(root.left)){
            sum = sum+root.left.val;
            count++;
        }else{
            sum = sum+traverse(root.left, count,sum);
        }
        sum = sum+traverse(root.right, count,sum);
        return sum;
    }

}
