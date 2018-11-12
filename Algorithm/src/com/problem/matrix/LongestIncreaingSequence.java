package com.problem.matrix;

import java.util.HashMap;
import java.util.Map;

public class LongestIncreaingSequence {
    public static void main(String[] args) {
        LongestIncreaingSequence seq = new LongestIncreaingSequence();
        int[] a= {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int op = seq.lis(a);
        System.out.println("op --> "+op);
    }
    public int lis(final int[] arr) {
        if(arr==null || arr.length==0){return 0;}
        else if(arr.length==1){return 1;}
        int[] op = new int[arr.length];
        for(int i =0;i<arr.length;i++){
            op[i]=1;
        }
        
        int j =0;
        int max =0;
        for(int i =1;i<arr.length;i++){
            j=0;
            while(j<i){
                if(arr[j]<arr[i]){
                arr[i] = Math.max(op[i],op[j]+1);
                
                }
                j++;
            }
            
        }
       for(int i =1;i<arr.length;i++){
           if(op[i]>max){max=op[i];}
       }
       return max;
    }
    public int lis1(final int[] a) {
        int max = 0;
        int n = a.length;
        for(int i=0;i<n;i++){
            if(max>(n-i)){break;}
            int count = getMax(a,i, Integer.MIN_VALUE, new HashMap<Integer,Integer>());
            if(count>max){max=count;}
        }
        return max;
    }
    
    public int getMax(int[] arr, int curr,int prev, Map<Integer,Integer> cache){
        int n = arr.length;
        if(curr>n-1){return 0;}
        if(arr[curr]<prev){return 0;}
        
        Integer val = cache.get(curr);
        if(val==null){
            val = getMax(arr,curr+1,arr[curr], cache);
            cache.put(curr,val);
        }
        return 1+val;
        
    }
}
