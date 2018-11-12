import java.util.Scanner;

public class PracticeCandidate {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
//        Scanner in = new Scanner(System.in);
//        final String fileName = System.getenv("OUTPUT_PATH");
//        String res;
//        int board_rows=0;
//        int board_cols=0;
//        board_rows = Integer.parseInt(in.nextLine().trim());
//        board_cols = Integer.parseInt(in.nextLine().trim());
//        
//        char[][] board = new char[board_rows][board_cols];
//        for(int i = 0; i<board_rows;i++) {
//            for(int j=0;j<board_cols;j++) {
//                board[i][j]=in.next().charAt(0);
//            }
//        }
//        
        
        char[][] boards= {{'R','R','R','R','0','0','0'},{'Y','Y','R','Y','Y','0','0'},{'R','R','R','Y','R','0','R'},{'Y','Y','Y','R','R','Y','R'},{'Y','R','Y','R','Y','Y','Y'},{'R','Y','R','Y','R','R','R'}};
        char[][] boards1= {{'0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0'},{'0','0','0','0','0','0','Y'},{'0','0','0','0','R','Y','R'},{'0','0','0','0','Y','Y','Y'},{'R','Y','R','Y','R','R','R'}};
        
        String op = findGameStatus(boards);
        System.out.println("Ouput - "+op);

    }
    
    static String findGameStatus(char[][] board) {
        
        int rowLength = board.length;
        int colLength = board[0].length;
        String resultR="";
        String resultY="";
        for(int row = 0; row<rowLength; row++) {
            
            for(int col=0;col<colLength;col++) {
                
                if("".equals(resultR) && board[row][col]=='R') {
                    if(isRowDone(board, colLength, row, col,'R') || isColDone(board, rowLength, row, col, 'R') || isDiagonalDone(board, rowLength, colLength, row, col, 'R')
                            || isDiagonalRevDone(board, rowLength, colLength, row, col, 'R')) {
                        resultR = "R";
                    }
                    
                }else if("".equals(resultY) && board[row][col]=='Y') {
                    if(row==1&& col==4)
                    if(isRowDone(board, colLength, row, col,'Y') || isColDone(board, rowLength, row, col, 'Y') || isDiagonalDone(board, rowLength, colLength, row, col, 'Y')
                            || isDiagonalRevDone(board, rowLength, colLength, row, col, 'Y')) {
                        resultY = "Y";
                    }
                }
            }
        }
        if(resultR.equals("") && resultY.equals("")) {
            return "N";
        } 
        if(resultR.equals("R") && resultY.equals("Y")) {
            return "B";
        }
        if(resultR.equals("R")) {
            return "R";
        }
        return "Y";
    }
    
    private static boolean isDiagonalDone(char[][] board, int rowLength, int colLength, int row, int col, char char1) {
        if((col<=colLength-4) && (row<=rowLength-4)) {
            boolean isAvail=true;
            int r=row;int c=col;
            for(int i =1;i<=3;i++) {
                if(board[++r][++c]!=char1) {
                    isAvail=false;
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private static boolean isDiagonalRevDone(char[][] board, int rowLength, int colLength, int row, int col, char char1) {
        if((col>=colLength-4) && (row<=rowLength-4)) {
            boolean isAvail=true;
            int r=row;int c=col;
            for(int i =1;i<=3;i++) {
                if(board[++r][--c]!=char1) {
                    isAvail=false;
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isRowDone(char[][] board, int colLength, int row, int col, char c) {
        if(col<=colLength-4) {
            boolean isAvail=true;
            for(int i =col+1;i<col+4;i++) {
                if(board[row][i]!=c) {
                    isAvail=false;
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
    private static boolean isColDone(char[][] board, int rowLength, int row, int col, char c) {
        if(row<=rowLength-4) {
            boolean isAvail=true;

            for(int i=row+1;i<row+4;i++) {
                if(board[i][col]!=c) {
                    isAvail=false;
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
