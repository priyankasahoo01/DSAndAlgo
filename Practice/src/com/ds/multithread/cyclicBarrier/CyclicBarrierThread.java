package com.ds.multithread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

 class AggregatorThread extends Thread{

    public void run() {
        System.out.println("In aggregator");
    }
 }
    class WorkerThread extends Thread{
        CyclicBarrier cyclicBarrier ;

        public WorkerThread(CyclicBarrier cyclicBarrier, String name) {
            super();
            this.cyclicBarrier = cyclicBarrier;
            this.setName(name);
            this.start();
        }
        public void run() {
            System.out.println("Entered "+getName());
            try {
                cyclicBarrier.await();
                System.out.println("After await "+getName());
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
 
 public class CyclicBarrierThread{
     public static void main(String[] args) {
         System.out.println("Entered main method");
         CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new AggregatorThread());
         new WorkerThread(cyclicBarrier, "2016");
        snooze();
         new WorkerThread(cyclicBarrier, "2017");
         snooze();
         new WorkerThread(cyclicBarrier, "2018");
         snooze();
         new WorkerThread(cyclicBarrier, "2019");
         System.out.println("Ended Main method");
     }

    private static void snooze() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 }
