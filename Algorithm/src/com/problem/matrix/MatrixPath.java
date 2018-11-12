package com.problem.matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixPath {
    class Point {
        int r;
        int c;
    }

    public static void main(String[] args) {
        int mat[][] = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
        List<String> li = new ArrayList<>();
        getPath(mat, 0, 0, "", li);

        for (String l : li) {
            System.out.println(l);
        }
    }

    private static boolean isValid(int[][] mat, int i, int j) {
        int m = mat.length;
        int n = mat[0].length;
        if ((i < 0 || i >= m) || (j < 0 || j >= n)) {
            return false;
        }
        if (mat[i][j] < 0) {
            return false;
        }
        return true;
    }

    private static void getPath(int[][] mat, int i, int j, String op, List<String> li) {
        int m = mat.length;
        int n = mat[0].length;

        if (!isValid(mat, i, j)) {
            op = null;
            return;
        }
        if (i == m - 1 && j == n - 1) {
            op = op + "," + mat[i][j];
            li.add(op);
            return;
        }

        getPath(mat, i + 1, j, op+","+mat[i][j], li);
        getPath(mat, i, j + 1, op+","+mat[i][j], li);
    }

    private static boolean outOfMat(int i, int j, int[][] mat) {
        if (i < 0 || j < 0 || mat[i][j] == -1) {
            return false;
        }

        return false;
    }

}
