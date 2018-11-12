package com.stack;

import com.queue.MyQueue;
import com.queue.Queue;

public class QueueToStack<T> implements Stack<T> {
    private Queue<T> queue;

    public QueueToStack(int size) {
        queue = new MyQueue<>(size);
    }

    @Override
    public void push(T data) throws Exception {
        int prevSize = queue.size();

        queue.enqueue(data);
        for (int i = 0; i < prevSize; i++) {
            T t = queue.dequeue();
            queue.enqueue(t);
        }
    }

    @Override
    public T pop() throws Exception {
        return queue.dequeue();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public T peek() throws Exception {
        return queue.getData();
    }

    @Override
    public boolean isFull() {
        return queue.isFull();
    }

    @Override
    public int getSize() {
        return queue.size();
    }

    @Override
    public void print() {
        queue.print();

    }

}
