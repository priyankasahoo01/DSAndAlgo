package com.problem;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BoxLine {

    public static void main(String[] args) {
        BoxLine b = new BoxLine();
        
        Box[] arr = new Box[4]; 
        arr[0] = b.new Box(4, 6, 7); 
        arr[1] = b.new Box(1, 2, 3); 
        arr[2] = b.new Box(4, 5, 6); 
        arr[3] = b.new Box(10, 12, 32);
        
        List<Box> boxList = Arrays.asList(arr);
        int max = b.getMax(boxList);
        System.out.println("max --> "+max);
        
    }
    
    class Box{
        int w,d,h;

        public Box(int w, int d, int h) {
            super();
            this.w = w;
            this.d = d;
            this.h = h;
        }
        
    }
    
    class BoxesComparator implements Comparator<Box>{

        @Override
        public int compare(Box o1, Box o2) {
            return o2.h - o1.h;
        }
        
    }
    public int getMax(List<Box> ip) {
        Collections.sort(ip, new BoxesComparator());
        int max =0;
        for(int i =0;i<ip.size();i++) {
            
            int size = getSize(ip,i,-1);
            if(size>max) {
                max = size;
            }
        }
        return max;
        
    }
    
    public int getSize(List<Box> ip, int curr, int prev) {
        int n = ip.size();
        if(curr>n-1) {
            return 0;
        }
        if(prev ==-1) {
            return 1+getSize(ip, curr+1, curr);
        }
        if(prev!=-1 && isEligible(ip,curr, prev)) {
            return 1+ getSize(ip,curr+1,curr);
        }
        return 0+getSize(ip, curr+1, prev);
    }
    
    public boolean isEligible(List<Box> ip, int curr, int prev) {
        Box b2 = ip.get(curr);
        Box b1 = ip.get(prev);
        if(b2.w<=b1.w && b2.h<=b1.h && b2.d<=b1.d) {
            return true;
        }
        return false;
    }
}
