package com.algo;

import java.util.ArrayList;
import java.util.List;

public class NQueen {
    
    public List<Integer[]> path(int n) {
        int[][] mat =new int[n][n];
        List<Integer[]> result = new ArrayList<>();
        while(i<n) {
            Integer[] cols = new Integer[n];
            
            boolean isPath = getPath(mat,0,0,cols);
            if(isPath) {
                result.add(cols);
            }
        }
        
        return result;
    }

    private boolean getPath(int[][] mat, int i, int j, Integer[] cols) {
        return false;
    }
}
