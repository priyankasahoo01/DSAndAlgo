package com.algo;

public class ReverseBit {

	public static void main(String[] args) {
		ReverseBit sol = new ReverseBit();
		long op = sol.reverse(3l);

		System.out.println("op --> "+op);
		System.out.println("test --> "+Long.parseLong("11000000000000000000000000000000",2));
	}

	public long reverse(long a) {
        for(int i = 0; i < 16; i++){
            long v1 = getkthBit(a, i);
            long v2 = getkthBit(a, 32-i-1);
            if((v1 ^ v2) == 0){
                continue;
            }else{
                a = toggle(a, i, 32-i-1);
            }
        }
        return a;
    }
    
	long getkthBit(long a , int k ){
        long val = a & (1 << k);
        return val == 0 ? 0 : 1;
    }
	
    long toggle(long a, int i , int j){
         a = a ^ (1<<i);
        a = a ^ (1<<j);
        return a;
    }
}
