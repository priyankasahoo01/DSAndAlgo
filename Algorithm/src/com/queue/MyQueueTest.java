package com.queue;

public class MyQueueTest {

    public static void main(String[] args) throws Exception {
        Queue<Integer> queue = new MyQueue<>(4);
        test(queue);
        
        Queue<Integer> stQu = new StackToQueue<>(4);
        //test(stQu);
    }

    private static void test(Queue<Integer> queue) throws Exception {
        System.out.println("-------------------------------------");
        System.out.println("size --> " + queue.size() + "\t" + "isfull --> " + queue.isFull() + "\t" + "isEmpty --> "
                + queue.isEmpty() + "\t");
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println("size --> " + queue.size() + "\t" + "isfull --> " + queue.isFull() + "\t" + "isEmpty --> "
                + queue.isEmpty() + "\t");
        try {

            queue.enqueue(7);
        } catch (Exception e) {
            System.out.println("Exception occurred while pushing data 7" + e.getMessage());
        }
        System.out.println("size --> " + queue.size() + "\t" + "isfull --> " + queue.isFull() + "\t" + "isEmpty --> "
                + queue.isEmpty() + "\t");
        Integer data = queue.dequeue();
        System.out.println("dequeue --> " + data + "size --> " + queue.size() + "\t" + "isfull --> " + queue.isFull()
                + "\t" + "isEmpty --> " + queue.isEmpty() + "\t");
        Integer data2 = queue.getData();
        System.out.println("getdata --> " + data2 + "size --> " + queue.size() + "\t" + "isfull --> " + queue.isFull()
                + "\t" + "isEmpty --> " + queue.isEmpty() + "\t");

        data = queue.dequeue();
        System.out.println("dequeue --> " + data + "size --> " + queue.size() + "\t" + "isfull --> " + queue.isFull()
                + "\t" + "isEmpty --> " + queue.isEmpty() + "\t");
        
        data = queue.dequeue();
        System.out.println("dequeue --> " + data + "size --> " + queue.size() + "\t" + "isfull --> " + queue.isFull()
                + "\t" + "isEmpty --> " + queue.isEmpty() + "\t");
        
        data = queue.dequeue();
        System.out.println("dequeue --> " + data + "size --> " + queue.size() + "\t" + "isfull --> " + queue.isFull()
                + "\t" + "isEmpty --> " + queue.isEmpty() + "\t");
        System.out.println("-------------------------------------");
    }

}
