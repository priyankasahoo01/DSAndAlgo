package com.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class N3RepeatNumber {

    public static void main(String[] args) {
        List<Integer> li = new ArrayList<Integer>();
        li.add(1);
        li.add(2);
        li.add(3);
        li.add(1);
        li.add(1);
        int op = repeatedNumber(li);
        System.out.println(op);
        
        int[] ip = new int[] {1000441, 1000441, 1000994};
        List<Integer> l = new ArrayList<Integer>();
        for(int i : ip) {
            l.add(i);
        }
        op = repeatedNumber(l);
        System.out.println(op);
        
        System.out.println((32^2));
        
    }
    
    public static int repeatedNumber(final List<Integer> arr) {
        if(arr == null || arr.size() == 0){return -1;}
        int n = arr.size();
        int op = (n%3 == 0)?(n/3):(n/3)+1;
        Collections.sort(arr);
        
        int count=1, max = 0;
        for(int i =1; i <n;i++){
            
            int curr = arr.get(i);
            int prev = arr.get(i-1);
            if(curr == prev){
                count++;
                if(count>=op){
                    return curr;
                }
            }else{
                count =1;
            }
            
        }
        return -1;
    }

}
