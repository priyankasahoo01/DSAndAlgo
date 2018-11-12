package com.linkedlist;

public class Utility {

    public static void printArr(int[] ip) {
        System.out.println("Print array");
        if (ip != null) {
            for(int i : ip) {
                System.out.print(i+" ");
            }
            System.out.println();
        }

    }

}
