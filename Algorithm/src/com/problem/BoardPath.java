package com.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardPath {
    public static void main(String[] args) {
        BoardPath pa = new BoardPath();
        boolean[][] board={{false, false, false, false},
                {true, true, false, true},
                {false, false, false, false},
                {false, false, false, false}};
        int[] start = {3,0};
        int[] end = {3,3};
        int op = pa.getMinPath(board, start, end, new ArrayList<String>(), new HashMap<String, Integer>());
        System.out.println("op --> "+op);
    }
    private boolean isFloor(boolean[][] mat, int i, int j) {
        return !mat[i][j];
    }

    private boolean outOfBound(boolean[][] mat, int i, int j) {
        int m = mat.length;
        int n = mat[0].length;
        if (i >= 0 && i <= m - 1 && j >= 0 && j <= n - 1) {
            return false;
        }
        return true;
    }

    private int[] arr(int ...mat ) {
        return mat;
    }
    public int getMinPath(boolean[][] board, int[] start, int[] end, List<String> visited, Map<String, Integer> cache){

        if(board==null || board.length==0){
            return 0;
        }
        
        int i1= start[0], j1=start[1],i2=end[0],j2=end[1];
        if(outOfBound(board,i1,j1) || outOfBound(board,i2,j2)){
            return  0;
            }
            if(visited.contains(string(i1,j1))){return 0;}
        if(!isFloor(board, i1,j1) || !isFloor(board, i2,j2)){
            return 0;
        }
        if(i1==i2 && j1==j2){
            return 1;
        }
        Integer val = cache.get(i1+","+j1);
        if(val !=null){
        return val;
        }
        visited.add(string(i1,j1));
        int min = 0;
        int maxExpected = Math.min(Math.abs(j2-j1), Math.abs(i2-i1));
        
        int bt = 1+getMinPath(board, arr(i1,j1+1), end, visited,cache);
        cache.put(string(i1,j1+1),bt);
        if(bt==maxExpected){return bt;}
        if(bt<min){min = bt;}
        
        int dn = 1+getMinPath(board, arr(i1+1,j1), end, visited,cache);
        cache.put(string(i1+1,j1),dn);
        if(dn==maxExpected){return dn;}
        if(dn<min){min = dn;}
        
        
        
        int up = 1+getMinPath(board, arr(i1-1,j1), end, visited,cache);
        cache.put(string(i1-1,j1),up);
        if(up==maxExpected){return up;}
        if(up<min){min = up;}
        
        int lt = 1+getMinPath(board, arr(i1,j1-1), end, visited,cache);
        cache.put(string(i1,j1-1),lt);
        if(lt==maxExpected){return lt;}
        if(lt<min){min = lt;}
        
        cache.put(string(i1,j1),min);
        return min;

        }

    private String string(int i, int j1) {
        // TODO Auto-generated method stub
        return i+","+j1;
    }

}
