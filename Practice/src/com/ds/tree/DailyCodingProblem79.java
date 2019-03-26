package com.ds.tree;
/**
 * This problem was asked by Facebook.

Given an array of integers, write a function to determine whether the array could become non-decreasing by modifying at most 1 element.

For example, given the array [10, 5, 7], you should return true, since we can modify the 10 into a 1 to make the array non-decreasing.

Given the array [10, 5, 1], you should return false, since we can't modify any one element to get a non-decreasing array.
 * @author priysaho
 *
 */
public class DailyCodingProblem79 {
	//1 3 5 4
	
	public static boolean isIncreasingSeqPossible(int... arr) {
		if(arr == null || arr.length <= 2 ) {
			return true;
		}
		boolean change = arr[0] > arr[1];
		for(int i = 1 ; i < arr.length - 1 ; i++) {
			int second = arr[i];
			int third = arr[i+1];
			if(second > third) {
				if(!change)change = true;
				else return false;
			}
		}
		return true;
		
	}

	public static void main(String[] args) {
		test(10, 5, 7);
		test(10, 5, 1);
		test(1, 3, 5, 4);
		test(1, 3, 5, 9);
		test(3, 1, 4, 8, 7, 9);
	}

	private static void test(int... arr) {
		boolean result = isIncreasingSeqPossible(arr);

		System.out.println(" op --> "+result);
	}

}
