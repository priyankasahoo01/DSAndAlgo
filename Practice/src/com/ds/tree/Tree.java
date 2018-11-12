package com.ds.tree;

import java.security.InvalidParameterException;

public class Tree {

    public enum TraverseType {
        INORDER, PREORDER, POSTORDER, PARALLEL
    }

    public Node root;

    public void add(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
        } else {
            Node local = root;
            while (local != null) {
                if (data < local.data) {
                    if (local.left == null) {
                        local.left = node;
                        break;
                    }
                    local = local.left;
                }
                if (data > local.data) {
                    if (local.right == null) {
                        local.right = node;
                        break;
                    }
                    local = local.right;
                }
            }

        }
    }

    public void traverse(TraverseType traverseType) {
        System.out.println("\n Traversing tree with traverse type: " + traverseType);
        if (traverseType == TraverseType.INORDER) {
            inOrderTraverse(root);
        } else if (traverseType == TraverseType.PREORDER) {
            preOrderTraverse(root);
        } else if (traverseType == TraverseType.POSTORDER) {
            postOrderTraverse(root);
        } else if (traverseType == TraverseType.PARALLEL) {
            System.out.print(root.data+"\t");
            parallelTraverse(root);
        } else {
            throw new InvalidParameterException("Traverstype " + traverseType + " is not supported");
        }

    }

    private void parallelTraverse(Node node) {
        if (node == null) {
            return;
        }
//        System.out.print(node.data + "\t");
        System.out.print((node.left==null?"":node.left.data)+"\t "+(node.right==null?"":node.right.data+"\t"));
        parallelTraverse(node.left);
        parallelTraverse(node.right);
    }

    private void preOrderTraverse(Node current) {
        if (current == null) {
            return;
        }
        if (isLeafNode(current)) {
            System.out.print(current.data+" , ");
            return;
        }
        System.out.print(current.data+" , ");
        preOrderTraverse(current.left);
        preOrderTraverse(current.right);
    }

    private void inOrderTraverse(Node current) {
        if (current == null) {
            return;
        }
        if (isLeafNode(current)) {
            System.out.print(current.data+" , ");
            return;
        }
        inOrderTraverse(current.left);
        System.out.print(current.data+" , ");
        inOrderTraverse(current.right);
    }

    private void postOrderTraverse(Node current) {
        if (current == null) {
            return;
        }
        if (isLeafNode(current)) {
            System.out.print(current.data+" , ");
            return;
        }
        postOrderTraverse(current.left);
        postOrderTraverse(current.right);
        System.out.print(current.data+" , ");
    }

    private boolean isLeafNode(Node local) {
        return (local.left == null) && (local.right == null);
    }

    public int depthOfTree() {
        return depth(root);
    }

    public int depth(Node node) {
        if (node == null) {
            return 0;
        }
        // if(node.left == null && node.right ==null){
        // return 1;
        // }
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            super();
            this.data = data;
        }

    }
    
    //  1
    //2         3
    //1,2,end,3,end
    //1
    //2 3
    //4 5       6       7
    //1,2,3,end,5,end,4,6,7,end
    public String serialize(Node node) {
        if(node == null) {
            return "END";
        }
        String val = String.valueOf(node.data);
        return val+"\t"+serialize(node.left)+"\t"+serialize(node.right);
    }
    
    
    public Node deserialize(String str) {
        if("END".equals(str)) {
            return null;
        }
        
//        Node node = new node()
        return null;
    }

}
