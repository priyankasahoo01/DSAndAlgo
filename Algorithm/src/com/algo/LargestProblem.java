package com.algo;

import java.util.Arrays;
import java.util.Comparator;

public class LargestProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LargestProblem sol = new LargestProblem();
		String op = sol.largestNumber(new int[] {3, 30, 34, 5, 9});
		System.out.println("output --> "+op);

	}

	public String largestNumber(final int[] ip) {
        Integer[] op = ip.clone();
         Arrays.sort(op, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				String first =o1+""+o2;
				String second =o2+""+o1;
				return second.compareTo(first);
			}
		});
        return stringConverter(op);
    }
    
    public String stringConverter(Integer[] op){
        String str="";
        for(int i=0;i<op.length;i++){
            str=str+""+String.valueOf(op[i]);
        }
        return str;
    }
    
    public void swap(int[] ip, int i, int j){
        int temp = ip[i];
        ip[i] = ip[j];
        ip[j] = temp;
    }
    
    public int[] sort1(int[] ip){
        
        for(int i =0; i < ip.length-1;i++){
        	for(int j =i+1; j<ip.length;j++) {
        		if(compare(ip[i],ip[j]) >= 0){
                    continue;
                }else{
                    swap(ip, i,i+1);
                }
        	}
            
        }
        return ip;
    }
    
    
    
    public int compare(int a , int b){
//    	Integer[] ip = new int[] {6,98,9};
//    	Object c;
//		Arrays.sort(ip, c);
        
        String aStr = Integer.toString(a);
        String bStr = Integer.toString(b);
        int lenA = aStr.length()-1;
        int lenB = bStr.length()-1;
        int i =0, j=0;
        while(i<=lenA && j<=lenB ){
            if(Character.getNumericValue(aStr.charAt(i))>Character.getNumericValue(bStr.charAt(j))){
                return 1;
            }
            else if(Character.getNumericValue(aStr.charAt(i))<Character.getNumericValue(bStr.charAt(j))){
                return -1;
            }else{
                i++;j++;
            }
        }
        
        if(j<lenB){
            return -1;
        }
        else if(i<lenA){
            return 1;
        }
        return 0;
    }
}
