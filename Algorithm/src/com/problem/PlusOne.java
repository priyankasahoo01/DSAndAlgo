package com.problem;

public class PlusOne {
    public static void main(String[] args) {
        PlusOne p = new PlusOne();
        int[] arr = { 1, 9, 9, 9, 9, 9, 9};
        test(p, arr);
        int[] i1 = {0,0,9,9};
        test(p,i1);
        
    }
    private static int[] test(PlusOne p, int[] arr) {
        int[] op = p.plusOne(arr);
        System.out.println("ip"+print(arr)+" op "+print(op));
        return op;
    }
    private static String print(int[] op) {
        String s ="";
        for(int i :op) {
            s = s+(i+" ");
        }
        return s;
    }
    public int[] plusOne(int[] arr) {
        int[] op = new int[arr.length];
        int carry = 1;
        int n = arr.length;
        
        
        
        int zero = 0;
        for(int i=0;i<n;i++){
            if(arr[i]==0){
                zero=zero+1;
            }else{
                
                break;
            }
            
        }
        
        
        for(int i = n-1; i>=zero;i--){
            int v = carry+arr[i];
            op[i] = v%10;
            
            if(v>=10){carry = 1;}
            else{carry = 0;}
        }
        
        
        int len = n-zero;
        if(zero==n){return new int[]{1};}
        if(carry == 1){
            int[] op1 = new int[len+1];
            op1[0]= 1;int j=1;
            for(int i=zero;i<n;i++){
                op1[j++]=op[i];
            }
            return op1;
        }
        if(zero>0){
            int[] op1 = new int[len];
            int j =0;
            for(int i=zero;i<n;i++){
                op1[j++]=op[i];
            }
            return op1;
        }
        
        
        return op;
    }

}
