package com.ds.graph;

public class Graph {

    int n;
    int[][] matrix;
    public Graph(int count) {
        n=count;
        matrix = new int[n][n];
    }
    
    public void add(int src, int dest) {
        matrix[src][dest]=1;
        matrix[dest][src]=1;
    }
    public void getShortestPath(int[][]arr, int[][] visited, int i, int j, int destX, int destY, int max, int sum) {
        
        
    }
}
