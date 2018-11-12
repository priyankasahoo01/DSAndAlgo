package com.problem;
/* Read input from STDIN. Print your output to STDOUT*/

import java.io.*;
import java.util.*;
public class Solution {
   public static void main(String args[] ) throws Exception {

    Scanner s = new Scanner(System.in);
    int n = 10;
    int m = 7;
    int r = 2;
    int[] notes= new int[n+1];
    int mat[][] = new int[m][2];
    for(int i =0; i <m; i++){
        for(int j =0;j<2;j++) {
            mat[i][j]=s.nextInt();
        }
    }
    for(int i =0; i <m; i++){
        int a = mat[i][0];
        int b = mat[i][1];
        
        if(notes[a]==0 && notes[b]==0){
            notes[a]=a;
            notes[b]=a;
        }else {
            if(notes[a]==0){
                notes[a]=a;
            }
            if(notes[b]!=0){
                int temp = b;
              while(notes[temp]!=temp){
                  temp=notes[temp];
              }
              notes[temp] = notes[a];
            }else {
                notes[b]=notes[a];
            }
        }
          
    }
    print(notes);
    //Notes array done filled
        int l = s.nextInt();
        int[] ip = new int[l];
        for(int k =0; k<l;k++){
            ip[k] = s.nextInt();
        }
        
        
        //compute
        int op = getMaxPalindromeLength(ip,notes);
        System.out.println(op);
        
   }
   
   public static int getVal(int[] notes, int n){
       if(n == notes[n]){
           return n;
       }
       int temp = n;
       while(notes[temp]!=temp){
           temp=notes[temp];
       }
       return notes[temp];
   }
   
   public static int getMaxPalindromeLength(int[] ip, int[] notes){
       LinkedList< Integer> ll = new LinkedList<>();
       ll.get
       for(int i =0;i<ip.length;i++){
           ip[i] = getVal(notes,ip[i]);
       }
       return fn(ip);
   }
   
   public static int fn(int[] ip){
       int n = ip.length;
        if(n <= 1){
            return 0;
        }else{
            if(ip[0] == ip[n-1]){
                return fn(Arrays.copyOfRange(ip, 1, n-1));
            }
            int[] ip1= Arrays.copyOfRange(ip, 0, n-1);
            int[] ip2= Arrays.copyOfRange(ip, 1, n);
            return 1+Math.min(fn(ip1),fn(ip2));
        }
   }
   
   public static void print(int[] ip) {
       System.out.println("-------");
       for(int i : ip) {
           System.out.print(i+" ");
       }
       System.out.println("-------");
   }
}
