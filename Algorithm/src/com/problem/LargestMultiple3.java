package com.problem;

public class LargestMultiple3 {
    public static void main(String[] args) {
        int[] ip = { 5, 8, 4, 1, 2, 0 };
        largestMultiple(ip);
        int[] ip2 = { 5, 4, 3, 1, 1 };
        largestMultiple(ip2);
        int arr[] = { 5, 5, 5, 7 };
        largestMultiple(arr);
        int[] arr1 = {2,5};
        largestMultiple(arr1);
        int[] arr2 = {7,4,1,1};
        largestMultiple(arr2);
    }

    private static void largestMultiple(int[] ip) {
        sortBySelectionSort(ip);
        // printArray(ip);
        int total = 0;
        int mod21 = 0, mod22 = 0;
        int mod11 = 0, mod12 = 0;

        for (int l = ip.length - 1; l >= 0; l--) {
            int i = ip[l];
            total = total + i;
            // if (mod12 == 0 && mod22 == 0) {
            // break;
            // }
            if (mod21 == 0 && (i % 3 == 2)) {
                mod21 = i;
            } else if (mod22 == 0 && (i % 3 == 2)) {
                mod22 = i;
            }

            if (mod11 == 0 && (i % 3 == 1)) {
                mod11 = i;
            } else if (mod12 == 0 && (i % 3 == 1)) {
                mod12 = i;
            }
        }
        System.out.println("total --> " + total + " mod11 -->" + mod11 + " mod12 --> " + mod12 + " mod21 -->" + mod21
                + " mod22 --> " + mod22);
        if (total % 3 == 0) {
            printArray(ip);
        } else if (total % 3 == 2) {
            if (mod21 != 0) {
                removeAndPrint(ip, mod21, -1);
            } else if (total % 3 == 2 && (mod22 != 0)) {
                removeAndPrint(ip, mod22, -1);
            } else if (mod11 != 0 && mod12 != 0) {
                removeAndPrint(ip, mod11, mod12);
            } else {
                System.out.println("not possible");
            }
        } else if (total % 3 == 1) {
            if (mod11 != 0) {
                removeAndPrint(ip, mod11, -1);
            } else if (mod12 != 0) {
                removeAndPrint(ip, mod12, -1);
            } else if (mod21 != 0 && mod22 != 0) {
                removeAndPrint(ip, mod21, mod22);
            } else {
                System.out.println("largest number --> not possible");
            }
        }

        else if (total % 3 == 1 && (mod11 != 0)) {

        } else if (total % 3 == 1 && (mod12 != 0)) {
            removeAndPrint(ip, mod12, -1);
        }

    }

    private static void removeAndPrint(int[] ip, int skip, int skip2) {
        boolean isSkipped1 = false, isSkipped2 = false;
        
        System.out.print("largest number --> ");
        for (int i : ip) {
            if (!isSkipped1 && i == skip) {
                isSkipped1 = true;
                continue;
            }

            if (skip2 != -1 && !isSkipped2 && i == skip2) {
                isSkipped2 = true;
                continue;
            }
            System.out.print(i);
        }
        if(!isSkipped1 && !isSkipped2) {
            System.out.print("not possible");
        }
        System.out.println();

    }

    private static void printArray(int[] ip) {
        for (int i : ip) {
            System.out.print(i + "\t");
        }
        System.out.println();

    }

    private static void sortBySelectionSort(int[] ip) {
        int[] temp = new int[10];
        for (int i : ip) {
            temp[i] = temp[i] + 1;
        }
        int j = 0;
        for (int i = temp.length - 1; i >= 0; i--) {
            for (int k = 0; k < temp[i]; k++) {
                ip[j++] = i;
            }

        }

    }

}
