package com.ds.multithread.reentrantlock;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainApplication {
    Lock lock = new ReentrantLock();
    String name;
    
    public MainApplication(String name) {
        this.name = name;
    }

    public void firstThread() {
        System.out.println("Entered first thread");
        increment();
    }
    
    private void increment() {
        System.out.println("Started increment thread : "+name);
        for(int i = 0;i<2000;i++) {
            
        }
        System.out.println("Ended increment thread : "+name);
    }

    public static void main(String[] args) {
        
        
    }

}
