package com.stack;

public class MyStack<T> implements Stack<T> {
    private T[] ip;
    private int top = -1;

    public MyStack(int size) {
        ip = (T[]) new Object[size];
    }

    @Override
    public void push(T data) throws Exception {
        if (isFull()) {
            throw new Exception("Can't push any new element. Stack is full to its max capacity " + getSize());
        }
        ip[++top] = data;
    }

    @Override
    public int getSize() {
        return top+1;
    }

    @Override
    public T pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty. Can't remove any element");
        }
        T data = ip[top];
        ip[top] = null;
        top--;
        return data;
    }

    @Override
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public T peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack is empty.");
        }
        return ip[top];
    }

    @Override
    public boolean isFull() {
        return (top == (ip.length-1));
    }

    @Override
    public void print() {
        for(T t : ip) {
            System.out.println(t+" \t");
        }
        System.out.println("\n");
    }
}
