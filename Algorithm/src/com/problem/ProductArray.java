package com.problem;

public class ProductArray {

    public static void main(String[] args) {

        int[] ip = { 2, 5, 8, 7, 6, 3 };
        System.out.println(print(ip) + " --> " + print(productArr(ip)));

    }

    public static String print(int[] arr) {
        String op = "";
        for (int i : arr) {
            op = op + i + " ";
        }
        return op;
    }

    public static int[] productArr(int[] ip) {
        if (ip == null || ip.length == 0) {
            return null;
        }
        int prod = 1;
        int n = ip.length;

        int op[] = new int[n];
        // from start
        for (int i = 0; i < n; i++) {
            op[i] = prod;// 1,2,10,
            prod = prod * ip[i];// 2,10
        }
        System.out.println("intermediate values --> " + print(op));
        // from end
        prod = 1;
        for (int i = n - 1; i >= 0; i--) {
            
            op[i] = prod*op[i];
            prod = prod * ip[i];
        }

        return op;
    }

}
