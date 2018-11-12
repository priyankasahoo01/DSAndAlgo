package com.heap;

public class MinHeap {
    int[] op;
    int current;
    int size;

    public MinHeap(int size) {
        super();
        this.size = size;
        op = new int[size + 1];
        current = 0;
    }

    public void createHeap(int[] ip) {
        for (int i = 0; i < size; i++) {
            insert(ip[i]);
        }
    }

    private void insert(int data) {
        if (current == 0) {
            op[current + 1] = data;
            current = 2;
        } else {
            op[current] = data;
            heapify(current);
            current++;
        }

    }

    private void heapify(int current) {
        int data = op[current];
        int parentIndex = current / 2;
        int parent = op[parentIndex];
        while ( parentIndex > 0 && data < parent) {
            swap(current, parentIndex);
            current = parentIndex;
            parentIndex = current/2;
            parent = op[parentIndex];
            
        }
    }

    private void swap(int current2, int parentIndex) {
        int temp = op[current2];
        op[current2] = op[parentIndex];
        op[parentIndex] = temp;
    }
    
    public void print() {
        System.out.println();
        for(int i : op) {
            System.out.print(i+" , ");
        }
    }
    
    
    public int extractMin(int level) {
        int min =op[1];
        while(level-- >0) {
            min = op[1];
            op[1] = op[current];
            op[current ] = 0
            
        }
        return min;
    }
}
