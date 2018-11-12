package com.heap;

public class MinHeapTest {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(11);
        int[] ip = {25,7,2,3,4,5,24,11,1,28,6};
        heap.createHeap(ip);
        heap.print();
    }

}
