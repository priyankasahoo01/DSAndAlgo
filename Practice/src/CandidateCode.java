import java.util.Scanner;

public class CandidateCode {

    public static void main(String args[]) throws Exception {

        // Write code here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int input[] = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = s.nextInt();
        }
        System.out.print(getMaxSumOfSubstring(input));
    }

    private static int getMaxSumOfSubstring(int[] input) {
        int length = input.length;
        int lastIndex = length - 3;
        int sum = -99999;
        for (int i = 0; i < lastIndex; i++) {
            int localSum = input[i];
            for (int j = i + 1; j < length; j++) {
                
                localSum = localSum +input[j];
                if (/*((j - i) >= 3) &&*/ (localSum > sum)) {
                    sum = localSum;
                }
            }
        }
        return sum;

    }
}
