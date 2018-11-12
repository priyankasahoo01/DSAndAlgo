package com.ds.thread;

public class OddEvenPrinter {
    private void printOdd() {
        for(int i=1;i<1000;i=i+2) {
            if(isOdd) {
                System.out.println(i);
                notify();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }
    }
    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter();
        Boolean isOdd = false;
        OddPrinter odd = printer.new OddPrinter(isOdd);
        EvenPrinter even = printer.new EvenPrinter(isOdd);
        
        Thread tOdd = new Thread(odd);
        Thread tEven = new Thread(even);
        
        tEven.start();
        tOdd.start();
        
    }
    class OddPrinter implements Runnable{
        public OddPrinter(Boolean bool) {
            isOdd=bool;
        }

        Boolean isOdd;
        @Override
        public void run() {
            printOdd();
            
        }
        
        
    }
    
    class EvenPrinter implements Runnable{
        public EvenPrinter(Boolean bool) {
            isOdd=bool;
        }
        Boolean isOdd;
        @Override
        public void run() {
            for(int i=0;i<1000;i=i+2) {
                if(!isOdd) {
                    System.out.println(i);
                    notify();
                }else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
            }
            
        }
        
    }

}
