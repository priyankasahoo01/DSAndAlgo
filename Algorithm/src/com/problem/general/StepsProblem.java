package com.problem.general;

public class StepsProblem {
    public static void main(String[] args) {
        steps(new int[] { 1, 2 }, 3);
        System.out.println("----");
        steps(new int[] { 1, 2, 3,4 }, 3);
        System.out.println("----");
        steps(new int[] { 4 }, 13);
        System.out.println("----");
    }

    public static void steps(int[] ip, int sum) {
        print(ip);
        int count = steps(ip, sum, "",0);
        System.out.println("count is "+count);
    }
    
    public static void print(int[] n) {
        System.out.print("[");
        for(int i:n) {
            System.out.print(i+",");
        }
        System.out.println("]");
    }

    public static int steps(int[] ip, int sum, String op, int count) {
        if (sum == 0) {
            System.out.println(op);
            return 1;
        }
        boolean notAvail = true;
        int tempCount =count;
        for (int i = 0; i < ip.length; i++) {
            if (ip[i] <= sum) {
                notAvail=false;
                count = count+steps(ip, sum - ip[i], op + ip[i] + ",",tempCount);
            }
        }
        if(notAvail) {
            System.out.println("Empty");
        }
        return count;
    }

}
