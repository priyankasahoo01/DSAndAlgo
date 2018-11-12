package com.ds.thread;

public class Task implements Runnable {

    private String name;

    public Task(String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            System.out.println(name + " -> " + i);
        }
    }

}
