package com.stack;

public class MyStackTest {

    public static void main(String[] args) throws Exception {
        Stack<Integer> stack = new MyStack<>(4);
        testStack(stack);
        stack = new QueueToStack(4);
        testStack(stack);
    }

    private static void testStack(Stack<Integer> stack) throws Exception {
        System.out.println("-----------------------------------------------------");
        System.out.println(
                "size --> " + stack.getSize() + " empty --> " + stack.isEmpty() + " full --> " + stack.isFull());
        stack.push(5);
        stack.push(59);
        stack.push(8);
        stack.push(6);
        System.out.println(
                "size --> " + stack.getSize() + " empty --> " + stack.isEmpty() + " full --> " + stack.isFull());
        try {
            stack.push(45);
        } catch (Exception e) {
            System.out.println("exception while pushing 45 " + e);
        }
        System.out.println(
                "size --> " + stack.getSize() + " empty --> " + stack.isEmpty() + " full --> " + stack.isFull());
        Integer d = stack.peek();
        System.out.println("peek --> " + d + " size --> " + stack.getSize() + " empty --> " + stack.isEmpty()
                + " full --> " + stack.isFull());
        d = stack.pop();
        System.out.println("pop --> " + d + " size --> " + stack.getSize() + " empty --> " + stack.isEmpty()
                + " full --> " + stack.isFull());
        System.out.println("-----------------------------------------------------");
    }

}
