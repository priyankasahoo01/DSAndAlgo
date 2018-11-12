package com.ds.heap;

public class MyHeapSort {

    public static void main(String[] args) {
        int ip[] = { 5, 9, 20, 3, 7, 10, 98, 1 };
        sort(ip);
        print(ip);
    }

    private static void print(int[] ip) {
        for (int i : ip) {
            System.out.println(i + " ");
        }
    }

    public static void sort(int[] ip) {
        int n = ip.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(ip, i, n);
        }
        for(int i =n-1; i >=0 ;i--) {
            swapIndex(ip, 0, i);
            heapify(ip, 0, i);
        }
    }

    private static void heapify(int[] ip, int i, int n) {
        int curr = ip[i];
        int lt = 2 * i + 1;
        int rt = 2 * i + 2;
        int minIndex = i;
        if (lt < n)
            if (ip[lt] < ip[i]) {
                minIndex = lt;
            }

        if (rt < n) {

            if (ip[minIndex] > ip[rt]) {
                minIndex = rt;
            }
        }
        if (minIndex == i) {
            return;
        }
        swapIndex(ip, i, minIndex);
        heapify(ip, minIndex, n);
    }

    private static void swapIndex(int[] ip, int i, int minIndex) {
        int swap = ip[i];
        ip[i] = ip[minIndex];
        ip[minIndex] = swap;
    }
}
