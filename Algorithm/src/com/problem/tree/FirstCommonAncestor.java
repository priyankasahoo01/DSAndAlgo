package com.problem.tree;

import java.util.ArrayList;
import java.util.List;

public class FirstCommonAncestor {
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
        test(node1, node4, node11);
        test(node1, node8, node2);
        test(node1, node1, node7);

        lca(node1, node4, node11);
        System.out.println("---");
        
        test(node1, node5, node9);
        
        test(node1, node5, new Node(10));
        
        System.out.println(findLca(node1, 5, 9, new ArrayList<Integer>()));
    }

    // Without parent link
    
    public static int findLca(Node root, int a, int b, List<Integer> li) {
    	int n1=-1,n2=-1;
    	if(root.val == a || root.val == b) {
    		n1=root.val;
    		li.add(n1);
    		n2 = findLca(root.left, a, b, li);
    		if(n2 == -1) {
    			n2 = findLca(root.right, a, b, li);
    		}
    		if(n2 == -1) {
    			return -1;
    		}else {
    			return n1;
    		}
    	}
    	n1=findLca(root.left, a, b,li);
    	n2 = findLca(root.right, a, b,li);
    	if(n1==-1 && n2==-1) {return -1;}
    	if(li.contains(a) && li.contains(b)) {
    		if(n1==-1) {return n2;}
    		if(n2==-1) {return n1;}
    		return root.val;
    	}
    	return -1;
    	
    }

    public static Node lca(Node root, Node n1, Node n2) {
        if (root == null) {
            return null;
        }
        if (root.val == n1.val) {
            return n1;
        }
        if (root.val == n2.val) {
            return n2;
        }
        Node searchLeft = lca(root.left, n1, n2);
        if(searchLeft !=n1 && searchLeft!=n2){
          //This is the LCA
              return searchLeft;
          }
          //otherwise do search

        Node searchRight = lca(root.right, n1, n2);
        if (searchLeft == null) {
            return searchRight;
        }
        if (searchRight == null) {
            return searchLeft;
        }
        return root;
    }

    private static void test(Node root, Node node4, Node node11) {
        Node op = fca(node4, node11);
        System.out.println("Ancestor of " + node4.val + " " + node11.val + " --> " + (op != null ? op.val : op));

        op = lca(root, node4, node11);
        System.out.println("Without parent link - Ancestor of " + node4.val + " " + node11.val + " --> " + (op != null ? op.val : op));
    }

    public static Node fca(Node n1, Node n2) {
        if (n1 == n2) {
            return n1;
        }
        if (n1 == null) {
            return null;
        }
        if (n2 == null) {
            return null;
        }
        int d1 = depth(n1);
        int d2 = depth(n2);
        int diff = Math.abs(d1 - d2);
        Node first, second;
        if (d1 > d2) {
            first = n1;
            second = n2;
        } else {
            second = n1;
            first = n2;
        }
        first = backtraverse(first, diff);
        // now both node at same level
        if (first == second) {
            return first;
        }
        Node p1 = first.parent;
        Node p2 = second.parent;
        return fca(p1, p2);
    }

    private static Node backtraverse(Node node, int diff) {
        if (diff == 0) {
            return node;
        }
        while (diff > 0) {
            node = node.parent;
            diff--;
        }
        return node;
    }

    private static int depth(Node node) {
        if (node == null)
            return 0;
        int depth = 0;
        while (node != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }

}
