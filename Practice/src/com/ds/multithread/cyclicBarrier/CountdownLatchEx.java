package com.ds.multithread.cyclicBarrier;

import java.util.concurrent.CountDownLatch;

public class CountdownLatchEx {
    class WorkerThread extends Thread{
        CountDownLatch latch;
        
        public WorkerThread(String name, CountDownLatch latch) {
            super(name);
            this.latch = latch;
            this.start();
        }

        public WorkerThread() {
            super();
            // TODO Auto-generated constructor stub
        }



        public void run() {
            try {
                if(this.getName().equals("t7")) {
                    Thread.sleep(2000);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            System.out.println("hello "+this.getName());
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountdownLatchEx c = new CountdownLatchEx();
//        WorkerThread t1 = c.new WorkerThread();
//        WorkerThread t2 = c.new WorkerThread();
//        t1.start();
//        t2.start();
//        System.out.println("Main ends "+Thread.currentThread().getName());
//        System.out.println("Main ends "+Thread.currentThread().getName()+"   "+Thread.currentThread().isAlive());
        
        
        CountDownLatch latch = new CountDownLatch(3);
        
        WorkerThread t3 = c.new WorkerThread("t3",latch);
        WorkerThread t4 = c.new WorkerThread("t4",latch);
        WorkerThread t5 = c.new WorkerThread("t5",latch);
        WorkerThread t6 = c.new WorkerThread("t6",latch);
        WorkerThread t7 = c.new WorkerThread("t7",latch);
        WorkerThread t8 = c.new WorkerThread("t8",latch);
//        t3.start();t4.start();t5.start();t6.start();t7.start();t8.start();
        latch.await();
        System.out.println("Main continues after latch");
        
//        WorkerThread t6 = c.new WorkerThread();
        
        
    }

}
