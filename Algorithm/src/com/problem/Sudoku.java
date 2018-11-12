package com.problem;

public class Sudoku {
    
    static boolean sudokuSolve(char[][] board) {
        // your code goes here
        int m = board.length;
        int n = board[0].length;
        for(int i =0; i <m ;i++){
          for(int j =0; j <n;j++){
            if(board[i][j]=='.'){continue;}
              boolean rowColValid = checkRowCol(board,i,j,board[i][j]);
              boolean boxValid = checkBox(board,i,j,board[i][j]);
              if(!(boxValid && rowColValid)){
                return false;
              }
            
          }
        }
                 return true;
      }
      
      private static boolean checkBox(char[][] board, int i, int j , char num){
        int row = i-(i%3);
        int col = j-(j%3);
        for(int r=row;r<row+3;r++){
          for(int c = col;c<col+3;c++){
            if(i==r && j==c){continue;}
            if(num == board[r][c]){
              return false;
            }
          }
        }
        return true;
      }

      private static boolean checkRowCol(char[][] board, int i, int j , char num){
        for(int col = 0; col<9;col++){
          if(col == j){continue;}
          if(board[i][col] == num){return false;}
        }
        for(int row = 0; row<9;row++){
          if(row==i){continue;}
          if(board[row][j] == num){return false;}
        }
        return true;
        
      }
      public static void main(String[] args) {
          
          char[][] board = 
              {{'.','.','.','7','.','.','3','.','1'},{'3','.','.','9','.','.','.','.','.'},{'.','4','.','3','1','.','2','.','.'},{'.','6','.','4','.','.','5','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','1','.','.','8','.','4','.'},{'.','.','6','.','2','1','.','5','.'},{'.','.','.','.','.','9','.','.','8'},{'8','.','5','.','.','4','.','.','.'}};
          test(board);
          
          char[][] b1=
              {{'.','.','.','7','.','.','3','.','1'},{'3','.','.','9','.','.','.','.','.'},{'.','4','.','3','1','.','2','.','.'},{'.','6','.','4','.','.','5','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','1','.','.','8','.','4','.'},{'.','.','6','.','2','1','.','5','.'},{'.','.','.','.','.','9','.','.','8'},{'8','.','5','.','.','4','.','.','.'}};
          test(b1); 
          
          char[][] b2 = 
              {{'.','2','3','4','5','6','7','8','9'},{'1','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'}};
          test(b2);
      }

    private static void test(char[][] board) {
        boolean flag = sudokuSolve(board);
          System.out.println(flag);
    }

}
