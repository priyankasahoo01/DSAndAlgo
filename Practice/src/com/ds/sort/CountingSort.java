package com.ds.sort;

public class CountingSort {

    //Assume data can vary from 0-20
    public static int[] countingSort(int[] ip){
        int[] aux = new int[20];
        int[] sortedArr = new int[ip.length];
        for (int i = 0; i < ip.length; i++) {
            int data = ip[i];
            aux[data]=aux[data]+1;
        }
        int index = 0;
        for (int i = 0; i < aux.length; i++) {
            for(int j = 1 ; j<=aux[i]; j++){
                sortedArr[index++] = i;
            }
        }
        return sortedArr;
        
    }
    
    public static void main(String[] args) {
        int[] ip = new int[]{7,10,2,8,7,19,6,2,11,1,6,2,3,8,14};
        System.out.println("un-sorted array");
        for (int i = 0; i < ip.length; i++) {
            System.out.print(ip[i]+" ");
        }
        int[] sortedArr = countingSort(ip );
        System.out.println("sorted array");
        for (int i = 0; i < sortedArr.length; i++) {
            System.out.print(sortedArr[i]+" ");
        }
    }
}
