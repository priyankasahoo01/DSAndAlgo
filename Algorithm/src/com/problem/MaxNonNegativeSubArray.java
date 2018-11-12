package com.problem;

public class MaxNonNegativeSubArray {
    public static void main(String[] args) {
        MaxContiguousArray a = new MaxContiguousArray();
        // int[] op = maxset(new int[] {-846930886, -1714636915, 424238335,
        // -1649760492});
        // System.out.println(Utility.print(op));
        // int[] op1 = maxset(new int[] {0,0,-1,0});
        // System.out.println(Utility.print(op1));
//        System.out.println(1967513926 + 1540383426);
//        System.out.println(1967513926 ^ 1540383426);
        System.out.println(Integer.MAX_VALUE-756898537);
        int[] arr = new int[] { 1967513926, 1540383426, -1303455736, -521595368 };
//        test(arr);
        test(new int[] {756898537, -1973594324, -2038664370, -184803526, 1424268980 });
    }

    private static void test(int[] arr ) {
        int[] op2 = maxset(arr);
        System.out.println(Utility.print(op2));
    }

    public static int[] maxset(int[] arr) {
        int n = arr.length;
        int max = 0, maxlen = 0;
        int sum = 0;
        int len = 0;
        int start = -1;
        int i = 0;
        for (i = 0; i < n; i++) {
            
            if (arr[i] < 0) {
                if (sum > max || (sum == max && len > maxlen) || (sum == max && len == maxlen && (i-len < start))) {
                    start = i - len;
                    max = sum;
                    maxlen = len;
                }
                len = 0;
                sum = 0;

            } else {
                len = len + 1;
                if(Integer.MAX_VALUE-sum<arr[i]){sum = Integer.MAX_VALUE;}
                else{sum = sum + arr[i];}

            }
        }
        if (sum > max || (sum == max && len > maxlen) || (sum == max && len == maxlen && (i-len < start))) {
            start = i - len;
            max = sum;
            maxlen = len;
        }
        return getOp(arr, start, maxlen);

    }

    private static int[] getOp(int[] arr, int start, int len) {
        if (start == -1) {
            return new int[] {};
        }
        int[] op = new int[len];
        for (int i = 0; i < len; i++) {
            op[i] = arr[start + i];
        }
        return op;
    }
}
