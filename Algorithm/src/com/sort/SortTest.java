package com.sort;

import com.linkedlist.Utility;

public class SortTest {

    public static void main(String[] args) {
        Sort sort = new QuickSort();
        int[] ip = { 10, 80, 30, 90, 40, 50, 70 };
        sort.sort(ip);
        Utility.printArr(ip);

        int[] sortedArr = { 10, 30, 40, 50, 70, 80, 90 };
        sort.sort(sortedArr);
        Utility.printArr(sortedArr);
    }
}
