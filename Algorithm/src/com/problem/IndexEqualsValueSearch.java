package com.problem;

public class IndexEqualsValueSearch {
    
    static int indexEqualsValueSearch(int[] arr) {
        // your code goes here
        if(arr==null || arr.length == 0){return -1;}
        
          return search(arr,0,arr.length-1);
          // strictly monotonically increasing sequence
          
          // manishsahni2000@gmail.com
          

        
        
        
      }

      static int search(int[] arr , int lt , int rt){
        if(lt>rt){return -1;}
        int mid = (lt+rt)/2;
        if(mid == arr[mid] && (mid == 0 || arr[mid-1]!=mid-1)){return mid;}
        if(arr[mid]>=mid){return search(arr,lt,mid-1);}
        return search(arr,mid+1,rt);
        Math.ab
      }
      
      public static void main(String[] args) {
          int[] arr = {-9,1,2,3,4,5};
          int op = indexEqualsValueSearch(arr);
          System.out.println(op);
      }

}
