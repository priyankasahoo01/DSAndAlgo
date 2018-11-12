package com.problem.matrix;

public class LargestRectangle {
    public static void main(String[] args) {
        LargestRectangle r = new LargestRectangle();
        int[][] mat=
            {
                    {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                    {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0},
                    {1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                  };
        System.out.println("ip");
        int max = r.maximalRectangle(mat);
        System.out.println("op --> "+max);
    }
    private int count(int[][] mat, int i,int j){
        if(mat == null){return 0;}
        //if(outOfBound(mat,i,j)){return 0;}
        if(mat[i][j]!=1){return 0;}
        int k=1;
        int m = mat.length;
        int n = mat[0].length;
        int count=1;
        while(((i+k)<m) && ((j+k)<n) && isSqr(mat,i,j,k) ){
            k++;
        }
        count = (count+(k-1))*(count+(k-1));
        int f = k;
        while(((i+k)<m)  && checkrow(mat,i+k,j,j+f)){
            count = count +(f);
            
        }
        while(((i+k)<m)  && checkcol(mat,j+k,i,i+f)){
            count = count +(f);
            
        }
        
        return count;
    }
    
    private boolean checkrow(int[][] mat, int row, int startC, int k){
        int m = mat.length;
        int n = mat[0].length;
        //row fixed,k+c
        for(int i =startC;(i<=startC+k && i<m);i++){
            if(mat[row][startC+k]!=1){
                return false;
            }
        }
        return true;
    }
    
    private boolean checkcol(int[][] mat, int col, int startR, int k){
        int m = mat.length;
        int n = mat[0].length;
        //col fixed,k+c
        for(int i =startR;(i<=startR+k && i<m);i++){
            if(mat[startR+k][col]!=1){
                return false;
            }
        }
        return true;
    }
    
    private boolean isSqr(int[][] mat, int startR, int startC, int k){
        int m = mat.length;
        int n = mat[0].length;
        //row fixed,k+r
        for(int i =startC;(i<=startC+k &&i<n);i++){
            if(mat[startR+k][i]!=1){
                return false;
            }
        }
        
        //col fixed,k+c
        for(int i =startR;(i<=startR+k && i<m);i++){
            if(mat[i][startC+k]!=1){
                return false;
            }
        }
        return true;
    }
    public int maximalRectangle(int[][] mat) {
        
        if(mat==null || mat.length==0){return 0;}
        int max = 0;
        int m = mat.length;
        int n = mat[0].length;
        int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int val = count(mat,i,j);
                if(val>max){max=val;}
            }
        }
        return max;
    }

}
