package com.problem.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Robot can travel only in DOWN and RIGHT direction. he has to reach from
 * top-left TO bottom-right Some off limit cells are there, on which it cant
 * step
 * 
 * @author priysaho
 *
 */
public class MatrixTraverseRobot {

    public static void main(String[] args) {
        int mat[][] = new int[][] { { 1, 2 ,3}, { 4, 5,6} };
        int count = countPath(mat, 0, 0);
        System.out.println("path cunt -> " + count);
        
        List<Point> li = new ArrayList<MatrixTraverseRobot.Point>();
        path(mat, 0, 0, li );
    }
    class Point{
        int r;
        int c;
        public Point(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }
    public static boolean path(int[][] mat, int i, int j, List<Point> li) {
        if(!isValid(mat, i, j)) {
            return false;
        }
        int m = mat.length;
        int n = mat[0].length;
        if(i==m && j==n) {
            
            li.add(getPoint(i, j));
            return true;
        }
        if(path(mat, i+1,j,li) || path(mat,i,j+1,li)) {
            li.add(getPoint(i, j));
            return true;
        }
        return false;
        
    }
    private static Point getPoint(int i, int j) {
        MatrixTraverseRobot mtr = new MatrixTraverseRobot();
        Point point = mtr.new Point(i,j);
        return point;
    }
    public static int countPath(int[][] mat, int i, int j) {
        if (!isValid(mat, i, j)) {
            return 0;
        }
        int m = mat.length;
        int n = mat[0].length;
        if (i == m-1 && j == n-1) {
            return 1;
        }
        return countPath(mat, i, j + 1)+ countPath(mat, i + 1, j);
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

    public static List<String> printPath(int[][] mat, int i, int j, int m, int n, List<String> path) {
        if (i == m && j == n) {
            path.add(mat[i][j] + ",");
            return path;
        }
        if (i >= m && j >= n) {
            return null;
        }
        if (mat[i][j] == -1) {
            return null;
        }
        if ((i + 1 < m) && (j < n) && mat[i + 1][j] != -1) {
            printPath(mat, i + 1, j, m, n, path);
        }
        if ((j + 1 < n) && (i < m) && mat[i][j + 1] != -1) {
            printPath(mat, i, j + 1, m, n, path);
        }
        return path;

    }

}
