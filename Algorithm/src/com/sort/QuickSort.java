package com.sort;

public class QuickSort implements Sort {

    @Override
    public void sort(int[] ip) {
        quicksort(ip, 0, ip.length - 1);
    }

    private void quicksort(int[] arr, int l, int h) {
        if (l == h) {
            return;
        }
        
        
        int index = partition(arr,l,h);
        if(l < index-1) {
            quicksort(arr, l, index-1);
        }
        if(index+1<h) {
            quicksort(arr, index+1, h);
        }
        
        
        
        
    }

    private int partition(int[] arr, int l, int h) {
        int pivot = arr[h];
        int j = h - 1;
        int i = l;
        while (i <= j) {
            while (arr[i] < pivot ) {
                i++;
            }

            while (arr[j] > pivot ) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
                i++;j--;
            }
                
        }
        swap(arr, i, h);
//        System.out.println("partition index --> "+i+"partition num --> "+arr[i]);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        System.out.println();
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]=temp;
        
    }

}
