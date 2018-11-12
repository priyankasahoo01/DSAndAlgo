package com.ds.thread;

public class TaskTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task("t1"));
        Thread t2 = new Thread(new Task("t2"));
        t1.start();
        t2.start();
    }
}
