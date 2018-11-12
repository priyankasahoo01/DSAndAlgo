package com.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MinMatrixPath {
    
    public static void main(String[] args) {
        MinMatrixPath pa = new MinMatrixPath();
        boolean[][] board={{false, false, false, false},
                {true, true, false, true},
                {false, false, false, false},
                {false, false, false, false}};
        boolean board1[][] = {{false,false}};
        
        HashSet<String> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        System.out.println("-->"+q.remove());
        System.out.println("-->"+q.remove());
        HashMap<String, Integer> cache = new HashMap<>();
        int op1 = minPath(board1, 0, 0, 0, 1, visited, cache);
        System.out.println("op1 --> "+op1);
        int op = minPath(board, 3,0,3,3, visited, cache);
        System.out.println("op --> "+op);
    }
    public static int minPath(boolean[][] arr, int i, int j , int x,int y, Set<String> visited, Map<String, Integer> cache) {
        if(arr == null || arr.length==0) {return 0;}
        if(visited.contains(str(i,j))) {
            return 0;
        }
        if(outOfBound(i,j,arr)) {return 0;}
        
        if(arr[i][j]==true || arr[x][y]==true) {return 0;}
        
        if(i==x && j==y) {return 1;}
        Integer val = cache.get(str(i,j));
        
        if(val == null) {
           
        
        int min = Integer.MAX_VALUE;
        visited.add(str(i,j));
        //move up
        int rt =minPath(arr, i, j+1, x, y, visited, cache);
        if(rt == 1) {
            cache.put(str(i,j),1+rt);
            return 1+rt;
        }
        if(rt>1&& rt<min) {
            min = rt;
        }
//        cache.put(str(i, j+1),rt);
        
        int lt =minPath(arr, i, j-1, x, y, visited, cache);
        if(lt == 1) {
            cache.put(str(i,j),1+lt);
            return 1+lt;
        }
        if(lt>1&& lt<min) {
            min = lt;
        }
//        cache.put(str(i, j-1),lt);
        
        int up =minPath(arr, i-1, j, x, y, visited, cache);
//        cache.put(str(i, j+1),up);
        if(up == 1) {
            cache.put(str(i,j),1+up);
            return 1+up;
        }
        if(up>1&& rt<min) {
            min = up;
        }
        
        int dn =minPath(arr, i+1, j, x, y, visited, cache);
//        cache.put(str(i+1, j),dn);
        if(dn == 1) {
            cache.put(str(i,j),1+dn);
            return 1+dn;
        }
        if(dn>1&& dn<min) {
            min = dn;
        }
        
        
//        int[] array = array(rt,lt,up,dn);
//        Arrays.sort(array);
//        int ind = 0;
//        while(ind<arr.length && array[i]<=0) {
//            min = array[ind];
//        }
        cache.put(str(i,j),1+min);
        return 1+min;
        
//        if(val!=1 ) {
//            val = minPath(arr, i, j-1, x, y, visited, cache);
//            cache.put(str(i, j-1),val);
//            if(val<min) {min = val;}
//            if(val!=1 ) {
//                
//            }
//        }
        
        }
        return 0;
    }

    private static int[] array(int ...is ) {
        // TODO Auto-generated method stub
        return is;
    }

    private static String str(int i, int j) {
        return i+","+j;
    }

    private static boolean outOfBound(int i, int j, boolean[][] arr) {
        return !(i>=0 && i<arr.length && j>=0 && j<arr[0].length);
    }

}
