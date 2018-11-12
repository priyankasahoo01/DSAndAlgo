package com.problem;

public class PascalTriangle {
    
    
    public static void main(String[] args) {
        solve(5);
    }
    public static int[][] solve(int n) {
        if(n==0){return new int[][]{{}};}
        if(n==1){return new int[][]{{1}};}
        int[][] arr = new int[n][];
        arr[0]=new int[1];
        arr[0][0]=1;
        for(int i =1; i<n;i++){
            int m =i+1;
            arr[i]=new int[m];
            arr[i][i]=1;
            arr[i][0]=1;
            for(int j = 1; j < m-1; j++){
                arr[i][j] = arr[i-1][j]+arr[i-1][j-1];
            }
        }
        
        return arr;
    }
    
    public int[] getRow(int n) {
        int[] op = new int[n+1];
        for(int i=0; i <=n;i++){
            op[i]=get(n,i);
        }
        return op;
    }
    
    public int get(int i , int j ){
        if(j==0){return 1;}
        if(i==j){return 1;}
        if(j==1){return i;}
        return get(i-1,j-1)+get(i-1,j);
    }

}
