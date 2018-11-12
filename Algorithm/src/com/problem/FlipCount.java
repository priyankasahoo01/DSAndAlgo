package com.problem;

public class FlipCount {
    public static void main(String[] args) {
        FlipCount c = new FlipCount();
        int[] op = c.flip("10110100010");
        System.out.println(Utility.print(op));
    }
    
    public int[] flip(String arr) {
        int n = arr.length();int count=0,lt=-1,rt=-1,max=0;
        for(int i =0;i<n;i++){
            if(arr.charAt(i)=='0'){
                count=count+1;
                if(count>max){max = count;lt=i+1;rt=i+1;}
                int j =i+1;
                for(j = i+1;j<n;j++){
                    if(arr.charAt(j)=='0'){
                        count=count+1;
                        if(count>max){max = count;lt=i+1;rt=j+1;}
//                        i=j;
                    }else{
//                        i=j;
                        count=count-1;
                        if(count<=0){
                            count=0;
                            break;
                        }
                    }
                    
                }
                i=j;
            }
        }
        if(max==0){return new int[]{};}
        else {return new int[]{lt,rt};}
    }
    
   

}
