package com.ds.test;

public class MatrixPath {
    static int m = 3, n = 3;
    static int a[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

    static int b[][] = { { 1, 0, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
    
    public static void main(String[] args) {
        path(0, 0, "");
        System.out.println(pathCount(0, 0, 0));
        System.out.println("binary matrix");
        pathB(0, 0, "");
        System.out.println(pathBCount(0, 0, 0));
    }

    private static int pathBCount(int i, int j, int count) {

        if(b[i][j] == 0){
            return 0;
        }
        if (i == m - 1 && j == n - 1) {
            count++;
            return count;
        }

        if (i == m - 1) {
            return pathBCount(i, j + 1, count);
        } else if (j == n - 1) {
            return pathBCount(i + 1, j, count);
        } else {
            return pathBCount(i + 1, j, count) + pathBCount(i, j + 1, count);
        }
    }

    public static void pathB(int i, int j, String path) {
        if(b[i][j] == 0){
            return;
        }
        path = path + "-" + b[i][j];
        if (i == m - 1 && j == n - 1) {

            System.out.println(path);
            return;
        }

        if (i == m - 1) {
            pathB(i, j + 1, path);
        } else if (j == n - 1) {
            pathB(i + 1, j, path);
        } else {
            pathB(i + 1, j, path);
            pathB(i, j + 1, path);
        }
    }
    
    public static void path(int i, int j, String path) {
        path = path + "-" + a[i][j];
        if (i == m - 1 && j == n - 1) {

            System.out.println(path);
            return;
        }

        if (i == m - 1) {
            path(i, j + 1, path);
        } else if (j == n - 1) {
            path(i + 1, j, path);
        } else {
            path(i + 1, j, path);
            path(i, j + 1, path);
        }
    }

    public static int pathCount(int i, int j, int count) {

        if (i == m - 1 && j == n - 1) {
            count++;
            return count;
        }

        if (i == m - 1) {
            return pathCount(i, j + 1, count);
        } else if (j == n - 1) {
            return pathCount(i + 1, j, count);
        } else {
            return pathCount(i + 1, j, count) + pathCount(i, j + 1, count);
        }
    }

}
