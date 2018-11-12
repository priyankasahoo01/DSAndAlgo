package com.ds;

public class MyQueue<T> {
    
    private T[] arr;
    private int top=-1, size;
    @SuppressWarnings("unchecked")
    public MyQueue(int size) {
        this.size = size;
        arr = (T[])new Object[size];
    }
    
    public void enqueue(T obj){
        if(isFull()) {
            try {
                throw new Exception("Queue is full");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return;
            }
        }
        arr[++top]=obj;
    }
    
    public T dequeue() {
        if(isEmpty()) {
            try {
                throw new Exception("Queue is empty");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
        T op = arr[0];
        arr[0]=null;
        for(int i=0;i<=top;i++) {
            arr[i]=arr[i+1];
        }
        top--;
        return op;
        
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return top==-1;
    }

    public boolean isFull() {
        return top==(size-1);
    }
    
    public void print(){
        for(T t : arr) {
            System.out.print(t+" ");
        }
    }
    public static void main (String[] args){
        MyQueue<Integer> q = new MyQueue<>(5);
        q.enqueue(1);
        q.enqueue(2);
        System.out.println("op "+q.dequeue());
        q.enqueue(3);
        System.out.println("op "+q.dequeue());
        System.out.println("op "+q.dequeue());
        System.out.println("op "+q.dequeue());
        q.enqueue(6);
        System.out.println("op "+q.dequeue());
        
        
    }

}
