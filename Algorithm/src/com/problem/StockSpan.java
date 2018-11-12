package com.problem;

import com.stack.MyStack;
import com.stack.Stack;

/**
 * The stock span problem is a financial problem where we have a series of n
 * daily price quotes for a stock and we need to calculate span of stock’s price
 * for all n days. The span Si of the stock’s price on a given day i is defined
 * as the maximum number of consecutive days just before the given day, for
 * which the price of the stock on the current day is less than or equal to its
 * price on the given day. For example, if an array of 7 days prices is given as
 * {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days
 * are {1, 1, 1, 2, 1, 4, 6}
 * 
 * @author priysaho
 *
 */
public class StockSpan {

    public static void main(String[] args) throws Exception {
        int[] ip = { 100, 80, 60, 70, 60, 75, 85 };
        int[] op = stockSpan(ip);
        printArr(op);
        int[] ip2 = {20,30,40,10,25,35,50,20};
        op = stockSpan(ip2);
        printArr(op);
        
    }

    private static void printArr(int[] op) {
        for(int i : op) {
            System.out.print(i+"\t");
        }
        System.out.println("\n");
    }

    private static int[] stockSpan(int[] ip) throws Exception {
        int size = ip.length;
        int[] op = new int[size];
        op[0]=1;
        Stack<Integer> stack = new MyStack<>(size);
        stack.push(0);
        for (int i = 1; i < ip.length; i++) {
            int peek = stack.peek();
            int count = 1;
            while(!stack.isEmpty() && (ip[peek] <= ip[i]) && peek!=-1) {
                
                peek = stack.pop();
                count = count+op[peek];
                if(!stack.isEmpty()) {
                    peek= stack.peek();
                }else {
                    peek = -1;
                }
                
            }
            op[i]=count;
            stack.push(i);
            
            
        }
        return op;
    }

}
