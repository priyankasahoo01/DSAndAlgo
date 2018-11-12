package com.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchRange {
    public static void main(String[] args) {
        SearchRange s = new SearchRange();
        List<Integer> li = new ArrayList<>();
        li.add(5);li.add(7);li.add(7);li.add(8);li.add(8);li.add(10);
        int[] ip = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
        List<Integer> li1 = new ArrayList<>();
        for(int i : ip) {
            li1.add(i);
        }
        
        System.out.println(s.searchRange(li1, 10));
    }
    
    private boolean bound(int i ,int size){
        if(i>=0 && i<=size-1){return true;}
        return false;
    }
    private int search(final List<Integer> a,int b,int start,int end){
        int n = (end-start+1);
        int mid = (end+start)/2;
        int val = a.get(mid);
        if(end<start){return -1;}
        if(!bound(start,a.size()) || !bound(end,a.size())){
            return -1;
        }
        if(val == b){return mid;}
        if(b<val){
            return search(a,b,start,mid-1);
        }else{
            return search(a,b,mid+1,end);
        }
        Integer.m
        
    }
    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
        int n = a.size();
        ArrayList<Integer> op = new ArrayList<>();
        
        int index = search(a,b,0,n-1);
        if(index == -1){
            op.add(-1);op.add(-1);
            return op;
        }
        int in1 = 0,in2=1;
        while((index-in1) >= 0 && a.get(index-in1) == b) {
            in1++;
        }
        
        while(index+in2 <= n-1 && a.get(index+in2) == b) {
            in2++;
        }
        op.add(index-in1+1);
        op.add(index+in2-1);
        
        
        
//        int count = index+1;
//        while((count)<n && a.get(count)==b){
//            count++;
//        }
//        op.add(count-1);
        return op;
        
    }
}


