package com.sort;

public class InsertionSort implements Sort {

    @Override
    public void sort(int[] ip) {
        if (ip != null) {
            for (int currIndex = 1; currIndex < ip.length; currIndex++) {
                for (int j = currIndex; j < currIndex; j--) {
                    if (ip[currIndex] < ip[j]) {
                        swap(ip, currIndex, j);
                        break;
                    }
                }
            }
        }
    }

    private void swap(int[] ip, int currIndex, int swapPos) {
        // curr=1, swap=0
        int temp = ip[currIndex];
        int i =currIndex;
        do {
            ip[i] = ip[i - 1];
            i--;
        }while(i>swapPos);
        ip[swapPos] = temp;
    }

}
