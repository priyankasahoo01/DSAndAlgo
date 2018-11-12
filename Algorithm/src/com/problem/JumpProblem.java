package com.problem;

public class JumpProblem {
    public static void main(String[] args) {
        JumpProblem p = new JumpProblem();
        int arr[] = {29, 0, 22, 30, 0, 7, 6, 0, 0, 0, 0, 7, 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 9, 17, 7, 3, 27, 1, 17, 0, 0, 0, 0, 4, 0, 0, 0, 6, 17, 0, 0, 0, 0, 2, 0, 0, 0, 8, 0, 0, 0, 1, 13, 0, 19, 0, 0, 13, 0, 26, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 18, 0, 0, 7, 0, 6, 26, 3, 15, 0, 0, 6, 0, 25, 0, 0, 8, 0, 19, 0, 0, 0, 0, 1, 0, 26, 0, 0, 0, 26, 28, 14, 0, 0, 0, 14, 0, 0, 0, 0, 26, 0, 0, 0, 1, 0, 19, 0, 29, 9, 16, 14, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 23, 6, 0, 0, 8, 24, 0, 0, 0, 0, 11, 0, 26, 0, 19, 0, 5, 0, 29, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 10, 0, 4, 16, 29, 0, 0, 0, 0, 0, 0, 0, 21, 18, 17, 0, 0, 0, 0, 1, 0, 0, 10, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 23, 0, 0, 14, 7, 24, 0, 0, 0, 0, 0, 0, 0, 29, 0, 8, 27, 0, 0, 0, 4, 0, 0, 0, 25, 0, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 29, 0, 0, 0, 1, 0, 4, 4, 17, 15, 0, 11, 15, 27, 23, 0, 0, 0, 2, 0, 15, 30, 26, 0, 0, 4, 0, 0, 21, 23, 0, 0, 0, 0, 19, 0, 0, 0, 0, 27, 14, 16, 0, 28, 0, 0, 0, 15, 0, 0, 7, 0, 0, 1, 0, 0, 30, 28, 0, 0, 2, 22, 20, 0, 0, 17, 8, 0, 0, 0, 11, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 27, 27, 9, 0, 7, 0, 0, 26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 19, 0, 4, 6, 2, 0, 0, 27, 19, 0, 14, 0, 0, 6, 0, 0, 0, 0, 0, 18, 0, 0, 0, 27, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 22, 5, 27, 24, 25, 0, 12, 0, 0, 26, 17, 5, 0, 0, 29, 21, 0, 0, 6, 3, 14, 0, 0, 0, 0, 0, 0, 11, 15, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 22, 0, 17, 22, 0, 0, 0, 1, 5, 20, 0, 0, 0, 22, 6, 21, 18, 0, 6, 7, 0, 1, 0, 0, 0, 18, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0};
        int op = p.canJump(arr);
        System.out.println("op --> "+op);
        
        
        boolean b = minJumps(arr, arr.length)>0;
        System.out.println("op --> "+(b?1:0));
    }
    
    
    private static int minJumps(int[] arr, int n) {  
        int jumps[] = new int[n];  // jumps[n-1] will hold the  
                                   // result 
        int i, j; 
               
        if (n == 0 || arr[0] == 0) 
             return Integer.MAX_VALUE;  // if first element is 0, 
                                       // end cannot be reached 
               
        jumps[0] = 0; 
               
        // Find the minimum number of jumps to reach arr[i] 
        // from arr[0], and assign this value to jumps[i] 
        for (i = 1; i < n; i++) 
        { 
            jumps[i] = Integer.MAX_VALUE; 
             for (j = 0; j < i; j++) 
             { 
                  if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) 
                  { 
                      jumps[i] = Math.min(jumps[i], jumps[j] + 1); 
                      break; 
                  } 
              } 
        } 
            return jumps[n-1]; 
        } 
    
   /* public int canJump(int[] arr) {
        if(arr == null || arr.length==0){return 0;}
        if(arr.length==1){return 1;}
        int n = arr.length;
        int start = arr[0];
        
        return jump(arr,0)?1:0;
    }
    
    public boolean jump(int[] arr, int i){
        int n = arr.length;
        int start =i+arr[i];
        boolean reach = false;
        if(arr[i]==0 && i!= (n-1)){return false;}
        if(arr[i]+i >=n-1){return true;}
        for(int k = i+1; k<=start;k++){
            if(k==n-1){return true;}
            if(k>n-1){break;}
            reach = (reach || jump(arr,k));
        }
        return reach;
    }*/
    
    public int canJump(int[] arr) {
        if(arr == null || arr.length==0){return 0;}
        if(arr.length==1){return 1;}
        if(arr[0]==0){return 0;}
        
        int n = arr.length;
        int[] op = new int[n];
        op[0]=1;int len = 0;
        for(int i=0;i<n-1;i++){
            int j = arr[i];
            if(i+j>=n-1)return 1;
            if(j==0 && op[i]==0){return 0;}
            //if(len<i+1){
                if(j!=0)
                len = fill(arr,op,i,len);
            //}
            if(len==n-1){return 1;}
            
        }
        return 0;
    }
    private int fill(int[] arr, int[] op, int i, int len){
        int val = arr[i];
        int n = arr.length;
        for(int j = Math.max(i+1,len+1); j<=i+val;j++ ){
            op[j] = op[i]+1;
            /*if(j==n-1){
                return op[j];
            }else{
                op[j] = op[i]+1;
            }*/
            len++;
        }
        return len;
    }

}
