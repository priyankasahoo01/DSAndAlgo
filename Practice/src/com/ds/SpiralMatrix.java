package com.ds;

public class SpiralMatrix {

    public static void main(String[] args) {
        int[] matrix[] = new int[3][4];
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = count++;
            }
        }
        System.out.println("-------------------------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
        printSpiralMatrix(matrix);
    }

    public static void printSpiralMatrix(int[][] matrix) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int startX = 0, endX = n;
        int startY = 0, endY = m;
        while (startX <= endX && startY <= endY) {
            for (int i = startX; i <= endX; i++) {
                System.out.print(matrix[startY][i]+"\t");
            }
            startY++;
            if(startY < endY){
                for (int j = startY; j <= endY; j++) {
                    System.out.print(matrix[j][endX] + "\t");
                }
                endX--;
            }
            
            if(startX < endX){
                for (int j = endX; j >= startX; j--) {
                    System.out.print(matrix[endY][j] + "\t");
                }
                endY--; 
            }
            
            //
            for (int j = endY; j >= startY; j--) {
                System.out.print(matrix[j][startX] + "\t");
            }
            startX++;
            
        }
    }
}
