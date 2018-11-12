package com.ds.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LCS {
    // Complete the longestCommonSubsequence function below.
    /*static int[] lcs1(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[][] mat = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    mat[i][j] = 0;
                } else if (a[i - 1] == b[j - 1]) {
                    mat[i][j] = 1 + mat[i - 1][j - 1];
                } else {
                    mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]);
                }
            }
        }
    }*/

    static int[] lcs(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[][] mat = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    mat[i][j] = 0;
                } else if (a[i - 1] == b[j - 1]) {
                    mat[i][j] = 1 + mat[i - 1][j - 1];
                } else {
                    mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]);
                }
            }
        }
        // print mat
        System.out.print("a --> ");
        for(int i : a) {
            System.out.print(i);
        }
        System.out.println();
        
        System.out.print("b --> ");
        for(int i : b) {
            System.out.print(i);
        }
        System.out.println();
        
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
        }
        // get the subsequence
        int i = m, j = n;
        int count = mat[m][n];
        int[] op = new int[count];
        int k = 0;
        while (k < count) {
            if (mat[i][j] == mat[i - 1][j]) {
                i = i - 1;
            } else if (mat[i][j] == mat[i][j - 1]) {
                j = j - 1;
            } else {
                op[k++] = a[i-1];
                i = i - 1;
                j = j - 1;
            }
        }

        return op;

    }

    static int[] longestCommonSubsequence(int[] a, int[] b) {
        // populate map with indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            List<Integer> indexes = map.get(a[i]);
            if (indexes == null) {
                indexes = new ArrayList<>();
                map.put(a[i], indexes);
            }
            indexes.add(i);
        }
        // scan over second list
        int maxCount = 0;
        int first = -1, last = -1;
        // int[] op = new int[a.length>b.length ? b.length : a.length];
        for (int i = 0; i < b.length; i++) {
            if ((b.length - i) < maxCount) {
                break;
            }
            List<Integer> indexes = map.get(b[i]);
            if (indexes == null) {
                continue;
            }
            for (int index : indexes) {
                // int start = a[index];
                if ((a.length - index) < maxCount) {
                    continue;
                }
                int bIndex = i;
                int tempCount = 0, lastIndex = 0;
                for (int j = index; j < a.length; j++, bIndex++) {
                    if (b[bIndex] != a[j]) {
                        break;
                    }
                    tempCount++;
                    lastIndex = j;
                }
                if (tempCount > maxCount) {
                    maxCount = tempCount;
                    first = indexes.get(0);
                    last = lastIndex;
                }

            }
        }

        int op[];
        if (first == -1) {
            op = new int[0];
            return op;
        }
        int ind = 0;
        op = new int[last - first + 1];
        for (int k = first; k <= last; k++) {
            op[ind++] = a[k];
        }
        return op;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // BufferedWriter bufferedWriter = new BufferedWriter(new
        // FileWriter(System.getenv("OUTPUT_PATH")));

        // String[] nm = scanner.nextLine().split(" ");
        //
        // int n = Integer.parseInt(nm[0]);
        //
        // int m = Integer.parseInt(nm[1]);
        //
        // int[] a = new int[n];
        //
        // String[] aItems = scanner.nextLine().split(" ");
        // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        //
        // for (int i = 0; i < n; i++) {
        // int aItem = Integer.parseInt(aItems[i]);
        // a[i] = aItem;
        // }
        //
        // int[] b = new int[m];
        //
        // String[] bItems = scanner.nextLine().split(" ");
        // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        //
        // for (int i = 0; i < m; i++) {
        // int bItem = Integer.parseInt(bItems[i]);
        // b[i] = bItem;
        // }

        int a[] = new int[] { 1, 2, 3, 4, 1 };
        int b[] = new int[] { 3, 4, 1, 2, 1, 3 };

        int[] result = lcs(a, b);
        for (int i : result) {
            System.out.print(i + " ");
        }

        // for (int i = 0; i < result.length; i++) {
        // bufferedWriter.write(String.valueOf(result[i]));
        //
        // if (i != result.length - 1) {
        // bufferedWriter.write(" ");
        // }
        // }
        //
        // bufferedWriter.newLine();
        //
        // bufferedWriter.close();

        scanner.close();
    }

}
