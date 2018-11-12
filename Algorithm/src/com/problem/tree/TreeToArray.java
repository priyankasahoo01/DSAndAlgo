package com.problem.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeToArray {
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
        
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        getAllNodes(node1, map, 1);
        printLevelWise(map);
    }
    
    private static List<Integer[]> permute(Integer[] ip, List<Integer[]> permu){
        if(ip == null ) {return permu;}
        
        
        return null;
        
    }
    
    private static void printLevelWise(Map<Integer, List<Integer>> map) {
        int level = 1;
        List<Integer> list = map.get(level);
        while(list!=null) {
            System.out.print("level "+level +"--> ");
            for(Integer i : list) {
                System.out.print(i+" ");
            }
            System.out.println();
            list = map.get(++level);
            
        }
        
    }

    public static void getAllNodes(Node root, Map<Integer, List<Integer>> map, int level) {
        if(root == null) {return;}
        List<Integer> levelNodes = map.get(level);
//        Integer[] nodes = (Integer[]) levelNodes.toArray();
        if(levelNodes == null) {
            levelNodes = new ArrayList<>();
            map.put(level, levelNodes);
        }
        levelNodes.add(root.data);
        getAllNodes(root.left, map, level+1);
        getAllNodes(root.right, map, level+1);
        
        
    }

}
