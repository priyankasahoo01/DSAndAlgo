package com.problem;

public class MaxDistanceArray {
    public static void main(String[] args) {
        int op = maximumGap(new int[] {1,10});
        System.out.println("op -> "+op);
    }
    public static int maximumGap(final int[] arr) {
        if(arr == null || arr.length == 0){return -1;}
        int n = arr.length;
        int[] ltMin = new int[n];
        int[] rtMax = new int[n];
        ltMin[0]=arr[0];
        rtMax[n-1] = arr[n-1];
        for(int i = 1; i <n ; i++){
            int min = Math.min(arr[i],ltMin[i-1]);
            ltMin[i]= min;
        }
        for(int i =n-2; i >=0 ; i--){
            int max = Math.max(arr[i], rtMax[i+1]);
            rtMax[i]=max;
        }
        int i =0, j=0;int max = -1;
        while(i<n && j<n){
            if(arr[i]>rtMax[j]){
                j++;i++;
            }else{
                if((j-i) > max){
                    max = (j-i);
                }
                j++;
            }
        }
        return max;
        
    }

}
