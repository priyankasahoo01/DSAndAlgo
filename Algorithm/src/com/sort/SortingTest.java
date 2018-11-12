package com.sort;

public class SortingTest {
    public static void main(String[] args) {
        System.out.println("Insertion sort");
        Sort sort = new InsertionSort();
        int[] ip = { 4, 3, 2, 10, 12, 1, 5, 6 };
        sort.sort(ip);
        printArr(ip);
    }

    private static void printArr(int[] ip) {
        System.out.println("Printing->");
        for(int i : ip) {
            System.out.print(i+"\t");
        }
    }
}
