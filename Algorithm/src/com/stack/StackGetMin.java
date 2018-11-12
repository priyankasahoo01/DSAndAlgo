package com.stack;

public class StackGetMin {
    Integer min;
    Stack<Integer> stack;

    public StackGetMin(int size) {
        stack = new MyStack<>(size);
    }

    public void push(Integer data) throws Exception {
        // int num = (int)data;
        //// if(num < min) {
        //// min = num;
        //// }
        // if(num<min) {
        // super.push((2*num - min));
        // }
        // Integer i = new Integer();
        // super.push();
        if (min == null || data < min) {
            int d = 2*data - min;
            stack.push(d);
            min = data;
        }else {
            stack.push(data);
        }
    }

    public Integer pop() throws Exception {
        int data = stack.pop();
        if(data < min) {
            int num = 2*min - data;
            min = data;
            return num;
        }
        return data;
//        String st = "ab";
//        st.
        
    }

//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public Integer peek() throws Exception {
//        return null;
//    }
//
//    @Override
//    public boolean isFull() {
//        return false;
//    }
//
//    @Override
//    public int getSize() {
//        return 0;
//    }
//
//    @Override
//    public void print() {
//
//    }

}
