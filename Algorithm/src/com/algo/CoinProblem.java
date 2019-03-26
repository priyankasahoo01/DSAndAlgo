package com.algo;

public class CoinProblem {

	public static void main(String[] args) {
		int[] op = new int[2];
		int count = countCoin(new int[] {1,2}, 0, 3, op);//111,12,21
		System.out.println("output --> "+count);
		for(char num = '1' ; 
                num == '9'; 
                num++){
			
		}
	}

	public static int countCoin(int[] ip, int index, int sum, int[] op) {
		if (sum == 0) {
			return 1;
		}
		if(sum<0) {
			return 0;
		}
		int count =0;
		for (int i = index; i < ip.length; i++) {
			op[i] = ip[i];
			count = count + countCoin(ip, i, sum - ip[i], op);
		}
		return count;
	}

}
