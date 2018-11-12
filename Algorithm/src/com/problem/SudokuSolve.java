package com.problem;

public class SudokuSolve {
    
    public static void main(String[] args) {}
    
    public int sudoku(char[][] arr) {
        
        if(arr == null || arr.length ==0 || arr.length!=arr[0].length) {return 0;}
        int m = arr.length;
        int n = arr[0].length;
        
        if(check(arr)) {
           getEligibleCandidates(arr);
        }
        return 0;
        
        
    }
    
    public void getEligibleCandidates(char[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        for(int i =0; i<m ; i++) {
            for(int j =0; j<n ;j++) {
                if(arr[i][j]=='.') {
                    
                }
            }
        }
    }

    private boolean check(char[][] arr, int i, int j) {
        // TODO Auto-generated method stub
        if(arr == null || arr.length ==0 || arr.length!=arr[0].length) {return false;}
        int m = arr.length;
        int n = arr[0].length;
        
        int num = arr[i][j];
        for(int k=0;k<n;k++) {
            if(j==k) {continue;}
            if(arr[i][k] == num) {return false;} 
        }
        for(int k =0; k <m ;k++) {
            if(j==k) {continue;}
            if(arr[k][j] == num) {return false;}
        }
        int row = i-i%3;
        int col = j-j%3;
        for(int r=row;r<row+3;r++){
          for(int c = col;c<col+3;c++){
            if(i==r && j==c){continue;}
            if(num == arr[r][c]){
              return false;
            }
          }
        }
        return true;
    }

}
