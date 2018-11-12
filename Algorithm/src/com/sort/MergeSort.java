package com.sort;

public class MergeSort implements Sort {

    @Override
    public void sort(int[] ip) {
        mergeSort(ip, 0, n - 1);

    }

    private void mergeSort(int[] ip, int l, int r) {
        int mid = (l + r) / 2;
        mergeSort(ip, l, mid);
        mergeSort(ip, mid + 1, r);
        merge(ip, l, r, mid);

    }

    private void merge(int[] ip, int l, int r, int mid) {
       for(int i )
    }

}
