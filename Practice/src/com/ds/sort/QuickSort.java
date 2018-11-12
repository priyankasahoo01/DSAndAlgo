package com.ds.sort;

public class QuickSort {
     public int[] sort(int[] arr,int l, int r) {
         int p = partition(l,r,arr);
         arr = sort(arr,l,r);
        return arr;
         
     }

    private int partition(int l, int r, int[] arr) {
        int pivot = arr[r];
        int h = r-1;
        while(l<h) {
            while(arr[l]<pivot) {
                l++;
            }
            while(arr[h]>pivot) {
                h--;
            }
            if(l<h) {
                
            }
            arr = swap(arr,l,h);
        }
        return 0;
    }

    private int[] swap(int[] arr, int l, int h) {
        int temp = arr[l];
        arr[l]=arr[h];
        arr[h]=temp;
        return arr;
    }

}
