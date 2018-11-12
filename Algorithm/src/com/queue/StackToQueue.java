package com.queue;

import com.stack.MyStack;

public class StackToQueue<T> implements Queue<T> {
    MyStack<T> stack;
    MyStack<T> reverseStack;
    
    public StackToQueue(int size) {
        stack = new MyStack<>(size);
        reverseStack = new MyStack<>(size);
    }

    @Override
    public void enqueue(T data) throws Exception {
        if(stack.isEmpty()) {
            swap(reverseStack, stack);
        }
        stack.push(data);
    }

    

    @Override
    public T dequeue() throws Exception {
        if(reverseStack.isEmpty()) {
            swap(stack, reverseStack);
        }
       if(!reverseStack.isEmpty()) {
           return reverseStack.pop();
       }
        return null;
    }

    @Override
    public int size() {
        if(!reverseStack.isEmpty()) {
            reverseStack.getSize();
        }
        return stack.getSize();
    }

    @Override
    public boolean isFull() {
        return (stack.isFull() || reverseStack.isFull());
    }

    @Override
    public T getData() throws Exception {
        if(!stack.isEmpty()) {
            return stack.peek();
        }else if(!reverseStack.isEmpty()){
            return reverseStack.peek();
        }else {
            throw new Exception("No data found to return ");    
        }
        
    }

    @Override
    public void print() {
        if(!stack.isEmpty()) {
            stack.print();
        }else if(!reverseStack.isEmpty()){
            reverseStack.print();
        }
        
    }

    @Override
    public boolean isEmpty() {
        return (stack.isEmpty() && reverseStack.isEmpty());
    }
    
    private void swap(MyStack<T> src, MyStack<T> dest) throws Exception {
        while(!src.isEmpty()) {
            dest.push(src.pop());
        }
    }

}
