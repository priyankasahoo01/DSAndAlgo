package com.queue;

public interface Queue<T> {

    public void enqueue(T data) throws Exception;
    public T dequeue() throws Exception;
    public int size();
    boolean isFull();
    T getData() throws Exception;
    void print();
    boolean isEmpty();
    
}
