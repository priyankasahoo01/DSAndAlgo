package com.problem;

import java.util.ArrayList;
import java.util.List;

public class AsciiDecode {
    
    public static void main(String[] args) {
        
        
        String ip="01";
        System.out.println(new Integer(ip));
        test("01");
        test("12");
        test("129");
        test("1291");
        test("111");
        test("1234");
        test("10");
        
    }

    private static void test(String ip) {
        List<String> result = new ArrayList<>();
        result = decode(ip);
        System.out.print("ip : "+ip+" op : ");
        for(String s : result) {
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println("count -->"+numDecodings(ip));
        
    }
    
    public static int numDecodings(String s) {
        int n = s.length();
        if(n == 0 || "0".equals("0")){
            return 0;
        }
        if(n == 1){
            return 1;
        }
       
        int count1 = numDecodings(s.substring(1));
        int count2 =0;
        String val = getVal(s.substring(0,2));
        if(val!=null){
            if(n>2){
                count2 = numDecodings(s.substring(2));
            }else{
                count2 = 1;
            }
            
        }
        
        return count1+count2;
        
        
    }

    private static List<String> decode(String ip) {
        List<String> result = new ArrayList<>();
        if(ip.length() == 1) {
            String res = getVal(ip);
            if(res!=null) {
                result.add(res);
            }
            
            return result;
        }
        
        //
        if(getVal(ip.substring(0,1))!=null) {
            result = decode(ip.substring(1));
            append(result, ip.substring(0,1));
        }
        
        
        if(ip.length() > 1) {
//            Integer num = new Integer(ip.substring(0, 2));
            
            if(getVal(ip.substring(0, 2))!=null) {
                List<String> res1 = new ArrayList<>();
                if(ip.length()>2) {
                    res1 = decode(ip.substring(2));
                }
                append(res1,ip.substring(0, 2) );
                
                result.addAll(res1);
            }
        }
        
        return result;
        
        
        
//        String res1 = ip.substring(0)+decode(ip.substring(1), result);
//        result.add(res1);
////        return res1;
//        
//        if(getVal(num)!=null) {
//            String decode = decode(ip.substring(2), result);
//            
//            String res = ip.charAt(0)+decode;
//            result.add(res);
//            return res;
//        }
    }
    
    private static String getVal(String s){
        Integer i = new Integer(s);
        switch(s){
        case "1":
            return "A";    
        case "2":
            return "B";
        case "3":
            return "C";
        case "4":
            return "D";
        case "5":
            return "E";
        case "6":
            return "F";
        case "7":
            return "G";
        case "8":
            return "H";
        case "9":
            return "I";
        case "10":
            return "J";
        case "11":
            return "K";
        case "12":
            return "L";
        case "13":
            return "M";
        case "14":
            return "N";
        case "15":
            return "O";
        case "16":
            return "P";
        case "17":
            return "Q";
        case "18":
            return "R";
        case "19":
            return "T";
        case "20":
            return "T";
        case "21":
            return "U";
        case "22":
            return "V";
        case "23":
            return "W";
        case "24":
            return "X";
        case "25":
            return "Y";
        case "26":
            return "Z";
        default:
            return null;
        }
    }
        
    private static void append(List<String> result, String substring) {
        if(result == null || result.size() == 0) {
            result.add(getVal(substring));
            return;
        }
        for(int i =0;i<result.size();i++) {
            String old = result.get(i);
            result.set(i, getVal(substring)+old);
        }
        
    }

    /*private static String getVal(int num) {
        if(num > 26 || num < 1) {
            return null;
        }
        switch(num) {
        case 1:
            return "a";
            
        case 2:
            return "b";
        case 3:
            return "c";
        case 4:
            return "d";
        case 5:
            return "e";
        case 6:
            return "f";
        case 7:
            return "g";
        case 8:
            return "h";
        case 9:
            return "i";
        case 10:
            return "j";
        case 11:
            return "k";
        case 12:
            return "l";
        case 13:
            return "m";
        case 14:
            return "n";
        case 15:
            return "o";
        case 16:
            return "p";
        case 17:
            return "q";
        case 18:
            return "r";
        case 19:
            return "s";
        case 20:
            return "t";
        case 21:
            return "u";
        case 22:
            return "v";
        case 23:
            return "w";
        case 24:
            return "x";
        case 25:
            return "y";
        case 26:
            return "z";
            
            
        }
        return null;
        
    }*/

}
