package com.queue;

import com.stack.MyStack;
import com.stack.Stack;
//incomplete... thing again. and come back
public class StackToQueue1<T> implements Queue<T> {
    private Stack<T> stack;

    public StackToQueue1(int size) {
         stack = new MyStack<>(size)
     }

    @Override
    public void enqueue(T data) throws Exception {
        stack.push(data);

    }

    @Override
    public T dequeue() throws Exception {
        for(int i =0; i <stack.getSize()-1; i++) {
            
        }
        return null;
    }

    @Override
    public int size() {
        return stack.getSize();
    }

    @Override
    public boolean isFull() {
        return stack.isFull();
    }

    @Override
    public T getData() throws Exception {
        return stack.peek();
    }

    @Override
    public void print() {
        stack.print();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

}
