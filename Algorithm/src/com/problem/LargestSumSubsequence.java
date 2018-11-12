package com.problem;

import java.util.Deque;

public class LargestSumSubsequence {
    
    public int[] largestSumSubsequence(int[] ip, int k) {
        int n = ip.length;
        if(k>=n) {return null;}
        //firstFindMaxElement
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int maxIndex =-1, maxIndex2=-1;
        for(int i =0; i <k;i++) {
            if(ip[i]>max1) {
                max1=ip[i];
                maxIndex = i;
                max2=max1;
            }else if(ip[i]>max2) {
                max2=ip[i];
                maxIndex2=i;
            }
        }
        
        //
        if(maxIndex ==0) {
            max1 = max2;
        }
        ip[0]= max1;
        int nextMaxIndex = (maxIndex==0 ? maxIndex2:maxIndex);
        
        for(int j = k;j<=n-k;j++) {
            nextMaxIndex = check(ip,j,j+k,nextMaxIndex);
        }
        
        
    }
    
    private int check(int[] ip, int start, int end, int nextMaxIndex) {
        
        if(ip[end]>ip[nextMaxIndex]) {
            nextMaxIndex = end;
        }
        if(nextMaxIndex!=start) {
            return nextMaxIndex;
        }
    }

}
