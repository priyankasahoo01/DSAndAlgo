package com.algo;

public class ConvertArray {
	public static void main(String[] args) {
		int count = convertCount(new int[] { 5, 3, 6, 9, 4 }, new int[] { 8, 5, 9, 14, 7 });
		System.out.println("op --> "+count);
	}
	
	public int findSolution(int[] src, int[] dest) {
		findCount(src,dest, 0, src.length-1);
	}

	private void findCount(int[] src, int[] dest, int start, int end) {
		minimizeArrByDiffCount();
		
	}

	public static int convertCount(int[] src, int[] dest) {
		int start = 0;
		int n = src.length;
		int count = 0;
		while (start < n) {
			Result result = findDiff(src, dest, start);
			count = count + result.minDiff;
			start = result.endIndex;
		}

		return count;
	}

	public static Result findDiff(int[] src, int[] dest, int start) {
		int min = Integer.MAX_VALUE;
		int i = start;
		for (i = start; i < src.length; i++) {
			if (src[i] != dest[i]) {
				if ((dest[i] - src[i]) < min) {
					min = dest[i] - src[i];
				}
			} else {
				break;
			}
		}

//		if(i==src.length) {return new Result(i,min);}

		// reduce
		if (i != src.length) {
			for (int j = start; j < i; j++) {
				src[i] = src[i] + min;
			}
		}

		return createResult(i, min);
	}

	static Result createResult(int endIndex, int count) {
		ConvertArray c = new ConvertArray();
		return c.new Result(endIndex, count);
	}
	

	class Result {
		int minDiff;
		int endIndex;

		Result(int end, int diff) {
			this.endIndex = end;
			this.minDiff = diff;
		}
	}

}
