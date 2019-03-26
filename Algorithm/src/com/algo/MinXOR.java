package com.algo;

import java.util.Arrays;

public class MinXOR {
	
	public static void main(String[] args) {
		MinXOR sol = new MinXOR();
		int op = sol.findMinXor(new int[] {12, 4, 6, 2});
		System.out.println("op --> "+op);
	}

	public int findMinXor(int[] arr) {// [ 15, 5, 1, 10, 2 ]-->//
		// [1, 2, 5, 10, 15 ]->[0, 1, 3, 4, 4]
		int min = Integer.MAX_VALUE;
		Arrays.sort(arr);
		int[] power = powerOf(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (min == Integer.MAX_VALUE || power[j - 1] == power[j]) {
					int xor = arr[i] ^ arr[j];
					if (min > xor)
						min = xor;
				} else {
					break;
				}
			}
		}
		return min;
	}

	int[] powerOf(int[] arr) {
		int[] op = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j <= 32; j++) {
				if (arr[i] < Math.pow(2, j)) {
					op[i] = j;
					break;
				}
			}
		return op;
	}
}
