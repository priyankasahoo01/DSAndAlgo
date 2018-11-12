package com.queue;

public class MyQueue<T> implements Queue<T> {
    private T[] ip;
    private int top = -1;

    public MyQueue(int size) {
        ip = (T[]) new Object[size];
    }

    @Override
    public void enqueue(T data) throws Exception {
        if (isFull()) {
            throw new Exception("Can't push any new element. Queue is full to its max capacity " + size());
        }
        ip[++top] = data;
    }

    @Override
    public boolean isFull() {
        if (ip != null) {
            boolean isfull = (top + 1) == ip.length;
            return isfull;
        }
        return false;

    }

    @Override
    public T dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("Can't dequeue any new element. ");
        }
        T data = ip[0];
        for (int i = 0; i <= top - 1; i++) {
            ip[i] = ip[i + 1];
        }
        ip[top] = null;
        top--;
        return data;
    }
    
    @Override
    public T getData() throws Exception {
        if (isEmpty()) {
            throw new Exception("Can't dequeue any new element. ");
        }
        return ip[0];
    }

    @Override
    public boolean isEmpty() {

        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }
    
    @Override
    public void print() {
        if(ip!=null) {
            for(T t : ip) {
                System.out.print(t +"\t");
            }
            System.out.println("\n");
        }
    }

}
