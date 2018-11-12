package com.problem.general;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    
    public static void main(String[] args) {
        String ip = "ABC";
        List<String> list = permu(ip);
        print(ip, list);
        ip="ABCD";
        list = permu(ip);
        print(ip, list);
        
        ip="AAB";
        list = permu(ip);
        print(ip, list); 
        
    }

    private static void print(String ip, List<String> list) {
        System.out.print("input --> "+ip + " output --> ");
        for(String res : list) {
            System.out.print(res+" ");
        }
        System.out.println();
    }
    
    public static String permutation(String str) {
        if(str.length() == 1) {
            return str;
        }
        
        return str.substring(0, 1)+permutation(str.substring(1, str.length()));
    }
    
    public static List<String> permu(String ip) {
        if(ip.length() == 1) {
            List<String> op = new ArrayList<>();
            op.add(ip);
            return op;
        }
        String init = String.valueOf(ip.charAt(0));
        String rem = ip.substring(1);
        List<String> op = permu(rem);
        return append(op, init);
    }

    private static List<String> append(List<String> list, String init) {
        List<String> result = new ArrayList<>();
        for(String op : list) {
            for(int i = 0 ; i <= op.length(); i++) {
                String re = op.substring(0,i)+init+op.substring(i,op.length());
                result.add(re);
            }
        }
        return result;
    }

}
