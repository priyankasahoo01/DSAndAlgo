package com.ds.thread;

public class NumberPrinter {
    boolean isOdd = true;

    public synchronized void printEven() {
        for (int i = 2; i <= 100; i = i + 2) {
            if (isOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(i + " ");
            isOdd=true;
            notifyAll();
        }
    }

    public synchronized void printOdd() {
        for (int i = 1; i <= 100; i = i + 2) {
            if (!isOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(i + " ");
            isOdd=false;
            notifyAll();
        }
    }
    
    class MyThread implements Runnable{
        NumberPrinter printer;
        boolean isOdd;
        

        public MyThread(NumberPrinter printer, boolean isOdd) {
            super();
            this.printer = printer;
            this.isOdd = isOdd;
        }


        @Override
        public void run() {
            if(isOdd) {
                printer.printOdd();
            }else {
                printer.printEven();
            }
            
        }
        
    }
    
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter();
        Thread tOdd= new Thread(printer.new MyThread(printer,true));
        Thread tEven= new Thread(printer.new MyThread(printer,false));
        tOdd.start();
        tEven.start();
    }

}
