package com.ds;

public class MyStack {
    private String[] arr;
    private int size, top;

    public MyStack(int n) {
        size = n;
        arr = new String[size];
        top = -1;
    }
    
    public void push(String ip) throws Exception{
        if(top == size-1){
            throw new Exception("Stack is already full");
        }
        arr[++top] = ip;
    }
    
    public String pop() throws Exception{
        if(top == -1){
            throw new Exception("Stack is empty");
        }
        size--;
        return arr[top--];
    }
    public static void main(String[] args){
        String n="-75";
        Integer i = Integer.parseInt(n);
        System.out.println("number - "+i);
    }
    
}
