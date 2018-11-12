package com.problem;

import java.util.HashMap;
import java.util.Map;

public class MinCostPath {

    public static void main(String[] args) {
        MinCostPath m = new MinCostPath();
        int[][] arr = { {1, 2, 3}, 
                {4, 8, 2}, 
                {1, 5, 3} };
        int op = m.getMinCostPath(arr);
        System.out.println("op --> "+op);
    }

    public int getMinCostPath(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return getMin(arr, 0, 0, new HashMap<String, Integer>());
    }

    private String str(int i, int j) {
        return i + "," + j;
    }

    private int getMin(int[][] arr, int i, int j, Map<String, Integer> cache) {
        int m = arr.length;
        int n = arr[0].length;
        if (i == m - 1 && j == n - 1) {
            return arr[i][j];
        }
        if (outOfBound(i, j, m, n)) {
            return 0;
        }
        Integer min = cache.get(str(i, j));
        if ( min != null) {
            return min;
        }
        min = Integer.MAX_VALUE;
        int dn = getMin(arr, i + 1, j, cache);
        int rt = getMin(arr, i, j + 1, cache);
        int dg = getMin(arr, i + 1, j + 1, cache);
        min = (dn!=0 && dn < min) ? dn : min;
        min = (rt!=0 && rt < min) ? rt : min;
        min = (dg!=0 && dg < min) ? dg : min;
        cache.put(str(i, j), arr[i][j] + min);
        return arr[i][j] + min;

    }

    private boolean outOfBound(int i, int j, int m, int n) {
        if (i >= 0 && i <= m - 1 && j >= 0 && j <= n - 1) {
            return false;
        }
        return true;
    }

}
