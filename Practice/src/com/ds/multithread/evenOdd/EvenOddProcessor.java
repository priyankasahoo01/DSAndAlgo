package com.ds.multithread.evenOdd;

public class EvenOddProcessor {
    class Printer{
        private static final int MAX = 50;
        boolean isOdd=false;
        
        public synchronized void printOdd() {
            System.out.println("printing odd");
            //if(isOdd) {
                for(int i =1; i<MAX; i=i+2) {
                    
                    if(!isOdd) {
                        try {
                            System.out.println("odd waiting");
                            wait();
                        }catch(Exception e) {
                            
                        }
                    }
                    System.out.println("Odd "+i);
                    isOdd=false;
                    notifyAll();
                    System.out.println("odd notify all");
                    
                }
           // }
        }
        
        public synchronized void printEven() {
            System.out.println("printing even");
           // if(!isOdd) {
                for(int i =0; i<MAX; i=i+2) {
                    if(isOdd) {
                        try {
                            System.out.println("even waiting");
                            wait();
                        }catch(Exception e) {
                            
                        }
                    }
                    
                    System.out.println("Even "+i);
                    isOdd=true;
                    notifyAll();
                    System.out.println("even notify all");
                }
        //    }
        }
        
    }
    class EvenOddPrinter implements Runnable{
        Printer printer;
        boolean isOdd;
        
        
        public EvenOddPrinter(Printer printer, boolean isOdd) {
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
        EvenOddProcessor processor = new EvenOddProcessor();
        
        Printer printer = processor.new Printer();
        
        Thread tEven = new Thread(processor.new EvenOddPrinter(printer, false));
        Thread tOdd = new Thread(processor.new EvenOddPrinter(printer, true));
        tEven.start();
        tOdd.start();
    }

}
