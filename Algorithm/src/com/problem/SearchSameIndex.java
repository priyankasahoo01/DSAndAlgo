package com.problem;

public class SearchSameIndex {
    public static void main(String args[]) {
        SearchSameIndex si = new SearchSameIndex();
        
    }
    public int findIndex(int[] arr){
        if(arr==null || arr.length==0){return 0;}
    int n = arr.length;
        int lo = 0, hi = n-1;
        while(lo<=hi){
            int mid = (lo+hi)/2;
            if(arr[mid] == mid){
        return  mid;
    }
    //move rt
    if(arr[mid] <= mid){
        lo = mid+1;
    }else {
        hi=mid-1;
    }
    }
    return -1;
    }


}
