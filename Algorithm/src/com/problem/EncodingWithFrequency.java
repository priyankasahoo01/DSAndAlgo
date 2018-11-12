package com.problem;

public class EncodingWithFrequency {
    
    public static void main(String[] args) {
        System.out.println(""+'A'+5);
        test("AAAABBBCCDAA");
        test("ABCD");
        test("A");
        test("AAAAAAA");
    }

    private static void test(String ip) {
        EncodingWithFrequency en = new EncodingWithFrequency();
        
        String op = en.encoding(ip);
        System.out.println(ip +" --> "+op);
    }
    
    public String encoding(String ip) {
        String op="";
        if(ip==null || ip.length()==0) {return ip;}
        int freq = 1;
        char prev=ip.charAt(0);
        for(int i = 1 ;i <ip.length();i++) {
            char curr = ip.charAt(i);
            if(curr == prev) {
                freq++;
            }else {
                op=op+freq+prev;
                freq=1;
                prev = curr;//b
            }
        }
        op = op+freq+prev;
        return op;
    }

}
