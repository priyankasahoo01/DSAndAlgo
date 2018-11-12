package com.problem;

import com.stack.MyStack;
import com.stack.Stack;

/**
 * Given an array, print the Next Greater Element (NGE) for every element. The
 * Next greater Element for an element x is the first greater element on the
 * right side of x in array. Elements for which no greater element exist,
 * consider next greater element as -1.
 * 
 * @author priysaho
 *
 */
public class NGE {
    public static void main(String[] args) throws Exception {
        int[] ip = new int[] { 34, 6, 9, 23, 5, 1, 90, 2 };
        nge(ip);
    }

    private static void nge(int[] ip) throws Exception {
        Stack<Integer> st = new MyStack<>(ip.length);
        st.push(ip[0]);
        for (int i = 1; i < ip.length; i++) {
            int next = ip[i];
            if (st.isEmpty() == false) {
                int pop = st.pop();

                while (pop < next) {

                    System.out.println(pop + " --> " + next);
                    if (st.isEmpty()) {
                        break;
                    }
                    pop = st.pop();
                }
                if (pop > next) {
                    st.push(pop);
                }
            }
            st.push(next);
        }
        while(st.isEmpty() == false) {
            System.out.println(st.pop() + " --> "+-1);
        }
    }

}
