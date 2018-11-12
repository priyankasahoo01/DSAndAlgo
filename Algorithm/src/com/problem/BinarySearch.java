package com.problem;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = { 2, 4, 7, 8, 10, 18 };
        test(arr);
        int[] arr1 = { 2, 4, 7, 8, 10, 18, 20 };
        test(arr1);
        
        

    }

    private static void test(int[] arr) {
        System.out.println("Test array");
        BinarySearch s = new BinarySearch();

        for (int i : arr) {
            System.out.println(i + " -> " + s.exactSearch(arr, i, false));
        }
        System.out.println(999 + " -> " + s.exactSearch(arr, 999, false));
        
        System.out.println("--------------Iterative Solution ----------------");
        for (int i : arr) {
            System.out.println(i + " -> " + s.exactSearch(arr, i, true));
        }
        System.out.println(999 + " -> " + s.exactSearch(arr, 999, true));
        
        System.out.println("next max");
        for(int i: arr) {
            System.out.println(i+" --> "+s.findExactOrNextMax(arr, 0, arr.length-1, i+1));
        }
        
        
        
    }

    public int exactSearch(int[] arr, int num, boolean isIterative) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if(!isIterative)
        return find(arr, 0, arr.length - 1, num);
        return findIterative(arr, 0, arr.length-1, num);
    }

    int findIterative(int[] arr, int lt, int rt, int num) {
        int mid = -1;
        if (arr == null || arr.length == 0) {
            return mid;
        }
        while(lt<=rt) {
             mid = (lt+rt)/2;
            if(arr[mid] == num) {
                return mid;
            }
            if(num > arr[mid]) {
                lt = mid+1;
            }else {
                rt = mid -1;
            }
        }
        return -1;
    }

    int find(int[] arr, int lt, int rt, int num) {
        if (lt > rt) {
            return -1;
        }
        int mid = (lt + rt) / 2;
        if (num == arr[mid]) {
            return mid;
        }
        if (num < arr[mid]) {
            return find(arr, 0, mid - 1, num);
        }
        return find(arr, mid + 1, rt, num);
    }
    
    //1,8 , 4
    int findExactOrNextMax(int[] arr, int lt, int rt , int num){
        int mid =-1;
        while(lt<= rt) {
             mid = (lt+rt)/2;
            if(arr[mid] == num) {
                return mid;
            }
            if(num < arr[mid]) {
                lt = mid-1;
            }
            else {
                rt = mid+1;
            }
        }
        return mid;
    }

}
