package com.stack;

public interface Stack<T> {
    public void push(T data) throws Exception;

    public T pop() throws Exception;

    public boolean isEmpty();

    public T peek() throws Exception;

    public boolean isFull();

    public int getSize();

    void print();
}
