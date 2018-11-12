package com.ds.test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Test {

//    public static void main(String[] args){
////        Set<String> s = new HashSet<>();
////        s.add(null);
////        System.out.println("iterator next : "+s.iterator().next());
//        Date date1 = new Date(1511414940000L);
//        System.out.println("date >> "+date1);
//        int[] arr = new int[4];
//        Arrays.sort(arr);
//    }
    
    
    public static void main(String[] args) {
//        int ip = {2, 3, 4, 2, 3, 6, 8, 4, 5};
//        sub
//        getMedian(, d, subArr)
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[] expenditure = new int[n];
        for(int expenditure_i = 0; expenditure_i < n; expenditure_i++){
            expenditure[expenditure_i] = in.nextInt();
        }
        
        
        
        int result = activityNotifications(expenditure, d);
        System.out.println(result);
        in.close();
    }
    
    private static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        for (int i = d+1; i < expenditure.length; i++) {
            if(isNotified(expenditure, i, d)){
                count++;
            }
        }

    return count;
}

    public static  boolean isNotified(int[] ip, int day, int d ){
        int end = day - 1, start = day - 1 - d;
        int median = 0;
        int[] subArr = new int[d];
        int j=0;
        for(int i = start ; i < end ; i++){
            subArr[j++] = ip[i];
        }
        median = getMedian(ip, d, subArr);
        
        if(ip[day] > 2*median){
            return true;
        }
        return false;

    }

    private static int getMedian(int[] ip, int d, int[] subArr) {
        int median = 0;
        Arrays.sort(subArr);
        if(d%2 == 0){
            int begin = d/2;
            int last = d/2 +1;
            median = (ip[begin]+ip[last])/2;
        }else{
            median = ip[d/2+1];
        }
        return median;
    }
    
}
