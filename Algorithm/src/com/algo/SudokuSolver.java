package com.algo;

public class SudokuSolver {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char [][]board = new char[][]{{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
		
		SudokuSolver sol = new SudokuSolver();
		sol.solveSudoku(board);
		System.out.println();

	}

	public void solveSudoku(char[][] board) {
        solveSudoku1(board);
    }
    public boolean solveSudoku1(char[][] board) {
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9 ; j++){
                if(board[i][j] == '.'){
                    for(char num = '1' ; num <= '9' ; num++){
                        boolean valid = isValid(board, i, j, num);
                        if(valid ){
                            board[i][j] = num;
                            if(solveSudoku1(board)){
                                return true;
                            }
                        }else{
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
        }
    }
        return true;
    }
    
    
    boolean isValid(char[][] board, int row , int col, char val){
        int x = row - row%3;
        int y = col - col%3;
        //char val = board[row][col];
        for(int i = 0; i < 9; i++){
            if( board[row][i] == val){
                return false;
            }
        }
        for(int i = 0; i < 9; i++){
            if(board[i][col] == val){
                return false;
            }
        }
        
        for(int i = x; i<x+3 ; i++){
            for(int j = y; j < y+3; j++){
                
                if(board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}
