package com.problem;

public class SpiralOrderMatrix {
    public int[][] generateMatrix(int n) {
        //int val = Math.pow(n,n);
        int mat[][] = new int[n][n];
        int count = 1;
        int start = 0, end = n;
        while(count<=n*n){
            count = fill(mat, start, end,count);
            start++;end--;
        }
        return mat;
    }
    public int fill(int[][] arr, int start, int end, int count){
        for(int i =start;i<end;i++){
            arr[start][i]=count;
            count++;
        }
       // start++;
        for(int i = start+1; i<end;i++){
            arr[i][end-1] = count;
            count++;
        }
        //end--;
        for(int i = end-2;i>=start;i--){
            arr[end-1][i] = count;
            count++;
        }
        //end--;
        for(int i =end-2;i>=start+1;i--){
            arr[i][start]=count;
            count++;
        }
        
        return count;
    }

}
