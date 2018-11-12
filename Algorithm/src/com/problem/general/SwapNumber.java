package com.problem.general;

public class SwapNumber {
    public static void main(String[] args) {
        swap(21, 35);
    }

    private static void swap(int i, int j) {
        System.out.println("Before swapping NUM1 --> "+i+" NUM2 --> "+j);
        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
        System.out.println("After swapping NUM1 --> "+i+" NUM2 --> "+j);
    }
}