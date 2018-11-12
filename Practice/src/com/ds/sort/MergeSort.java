package com.ds.sort;

public class MergeSort {

    public static void main(String[] args) {
        int[] ip = { 2, 1, 7, 10, 9, 11, 6, 5 };
        System.out.println("iterate over input array");
        for (int i = 0; i < ip.length; i++) {
            System.out.print(ip[i] + "\t");
        }
        mergeSort(ip);
        System.out.println("\niterate over sorted array");
        for (int i = 0; i < ip.length; i++) {
            System.out.print(ip[i] + "\t");
        }
    }

    private static void mergeSort(int[] ip) {

        sort(ip, 0, ip.length - 1);
    }

    private static void sort(int[] ip, int l, int r) {
        if (l == r) {
            return;
        }
        if (l < r) {
            int middle = (r + l) / 2;
            sort(ip, l, middle);
            sort(ip, middle + 1, r);
            merge(ip, l, r, middle);
        }

    }
    
    

    private static void merge(int[] ip, int l, int r, int middle) {
        int index = 0;
        int leftArrLength = middle - l + 1;
        int[] tempLeft = new int[leftArrLength];
        int rightArrLength = r - middle;
        int[] tempRight = new int[rightArrLength];
        for (int i = l; i <= middle; i++) {
            tempLeft[index++] = ip[i];
        }
        index = 0;
        for (int i = middle + 1; i <= r; i++) {
            tempRight[index++] = ip[i];
        }
        int leftIndex = 0, rightIndex = 0;
        for (int i = l; i <= r; i++) {
            if (leftIndex >= leftArrLength) {
                ip[i] = tempRight[rightIndex++];
            } else if (rightIndex >= rightArrLength) {
                ip[i] = tempLeft[leftIndex++];
            } else if (tempLeft[leftIndex] < tempRight[rightIndex]) {
                ip[i] = tempLeft[leftIndex++];
            } else {
                ip[i] = tempRight[rightIndex++];
            }
        }

    }

}
