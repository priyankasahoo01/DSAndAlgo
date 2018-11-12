package com.problem.general;

public class CoinChange {

    public static void main(String[] args) {
//        int[] coins = { 1, 2 };// 1111,112,121,211,22
//        test(coins, 4);
//
//        int[] coins1 = { 1, 2, 5 };
//        test(coins1, 5);
        
        test(new int[]{1,2},3);
    }

    private static void test(int[] coins, int sum) {
        int op = coinChange(coins, sum, 0);
        System.out.println("numb of comb is : " + op);
    }

    private static int coinChange(int[] coins, int sum, int index) {
        if (sum == 0) {
            return 1;
        } else if (sum < 0) {
            return 0;
        }
        int tempsum = 0;
        for (int i = index; i < coins.length; i++) {
            tempsum = tempsum + coinChange(coins, sum - coins[i], i);
        }
        return tempsum;

    }

}
