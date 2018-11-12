package com.problem;

import java.util.Scanner;

/**
 * In a party of N people, only one person is known to everyone. Such a person
 * may be present in the party, if yes, (s)he doesn’t know anyone in the party.
 * We can only ask questions like “does A know B? “. Find the stranger
 * (celebrity) in minimum number of questions.
 * 
 * We can describe the problem input as an array of numbers/characters
 * representing persons in the party. We also have a hypothetical function
 * HaveAcquaintance(A, B) which returns true if A knows B, false otherwise.
 * 
 * @author priysaho
 *
 */
public class CelebrityProb {
    /* 
     * Enter your code here. Read input from STDIN. Print your output to STDOUT. 
     * Your class should be named CandidateCode.
    */
        public static void main(String args[] ) throws Exception {

            Scanner s = new Scanner(System.in);
            int size = s.nextInt();
            String ip = s.next();
            System.out.println(getNumber(ip,size));
       }
       public static int getNumber(String ip, int size){
           int mid = size/2;
           int low1 = 0, high1=-1, low2=-1, high2=size-1;
           if(size%2==0){
               //even
               high1=mid-1;
               low2=mid;
           }else{
              //odd
              high1=mid-1;
              low2=mid+1;
           }
           int count = 0;
           for(int i = low1; i <= high1; i++){
               char c1 = ip.charAt(i);
               char c2 = ip.charAt(high2--);
               if(c1 != c2){
                   count++;count++;
               }
               
           }
           return count;
       }

}
