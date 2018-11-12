package com.ds.sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class SpiralMatrix {

    static String printElementsInSpiralOrder(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        // System.out.print("[");
        String op = "[";
        int row = matrix.length;
        int col = matrix[0].length;
        int startR = 0, endR = row;
        int startC = 0, endC = col;
        while (startR < endR && startC < col) {
            for (int i = startC; i < endC; i++) {
                op = op + matrix[startR][i] + " ";
            }
            startR++;
            for (int i = startR; i < endR; i++) {
                op = op + matrix[i][endC - 1] + " ";
            }
            endC--;
            if (startR < endR) {
                for (int i = endC - 1; i >= startC; --i) {
                    op = op + matrix[endR - 1][i] + " ";
                }
                endR--;
            }

            if (startC < endC) {
                for (int i = endR - 1; i >= startR; --i) {
                    op = op + matrix[i][startC] + " ";
                }
                startC++;
            }
        }
        op = op.trim() + "]";
        return op;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        } else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        String res;
        int matrix_rows = 0;
        int matrix_cols = 0;
        matrix_rows = Integer.parseInt(in.nextLine().trim());
        matrix_cols = Integer.parseInt(in.nextLine().trim());

        int[][] matrix = new int[matrix_rows][matrix_cols];
        for (int matrix_i = 0; matrix_i < matrix_rows; matrix_i++) {
            for (int matrix_j = 0; matrix_j < matrix_cols; matrix_j++) {
                matrix[matrix_i][matrix_j] = in.nextInt();

            }
        }

        if (in.hasNextLine()) {
            in.nextLine();
        }

        res = printElementsInSpiralOrder(matrix);
        bw.write(res);
        bw.newLine();

        bw.close();
    }
}
