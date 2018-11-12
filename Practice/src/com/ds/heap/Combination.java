package com.ds.heap;

public class Combination {
    
    //ABCD - get all combination of 4 letter words
    //ab - a,b,ab,ba
    String[] allCombination = getCombination("ABCD");

    private static String[] getCombination(String string) {
        print(string.split(""));
        
        return null;
    }
    
    private static void print(String[] split) {
        for(String s : split){
            System.out.print(s+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        getCombination("abcd");
    }

}
