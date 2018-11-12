package com.problem;

public class MaxContiguousArray {

    public static void main(String[] args) {
        int ip[] = new int[] { -2, -3, 4, -1, -2, 1, 5, -3 };
        getMaxContigousArray(ip);
        int ip1[] = new int[] { 56, -200, -3, 4, -1, -2, 1, 5, -9, 23, -89, 0, 7, 32 };
        getMaxContigousArray(ip1);
    }

    private static void getMaxContigousArray(int[] ip) {
        int sum = ip[0];
        int finalSum = sum;

        for (int i = 1; i < ip.length; i++) {
            if ((ip[i] + sum) > ip[i]) {
                sum = sum + ip[i];
            } else {
                sum = ip[i];

            }
            if (sum > finalSum) {
                finalSum = sum;
            }
        }

        System.out.println("Max sum of array is : " + finalSum);
    }

}
