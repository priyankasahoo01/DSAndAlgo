package com.ds.heap;

public class MinHeap {
    int[] op;
    int current;
    int size;

    public MinHeap(int size) {
        this.size = size;
        op = new int[size + 1];
        current = 0;
    }

    public void createHeap(int[] ip) {
        for (int i = 0; i < ip.length; i++) {
            insert(ip[i]);
        }
    }

    private void insert(int i) {
        if (current == 0) {
            op[current + 1] = i;
            current = 2;
        } else {
            // int[] arr = {5,9,20,3,7,10,98,1};
            op[current] = i;
            heapify();
            if (current < size)
                current++;
        }
    }

    private void heapify() {
        int root = op[current / 2];
        if (root < op[current]) {
            return;
        }
        int present = current;
        int presentRoot = current / 2;
        while (op[present] < op[presentRoot]) {
            swapDataAtIndex(present, presentRoot);
            present = presentRoot;
            presentRoot = present / 2;
        }
    }

    private void minHeapify(int i) {
        if (i > size - 1) {
            return;
        }
        if (isLeafNode(i)) {
            return;
        }
        // int a = op[i];
        // int minChildIndex = getMinChildIndex(i);
        // int minChild = op[minChildIndex];
        // if (a < minChild) {
        // return;
        // }
        while (!isLeafNode(i)) {
            int a = op[i];
            int minChildIndex = getMinChildIndex(i);
            int minChild = op[minChildIndex];
            if (a < minChild) {
                break;
            }
            swapDataAtIndex(i, minChildIndex);
            i = minChildIndex;

        }
        // while (a > minChild && !isLeafNode(i)) {
        // swap(minChildIndex, i);
        // if(!isLeafNode(2*i)){
        // i = minChildIndex;
        // a=op[i];
        // minChildIndex = getMinChildIndex(i);
        // minChild = op[minChildIndex];
        // }
        //
        //// if(!isLeafNode(2*i)){
        //// minChild = minChildIndex;
        //// }
        //
        //
        // }

    }

    public int extractMin(int k) {
        int min = op[1];
        while (k-- > 0) {
            min = op[1];
            op[1] = op[current];
            op[current] = 0;
            minHeapify(1);
            System.out.println("after meanheapify");
            for (int i : op) {
                System.out.print(i + " , ");
            }

            current--;

        }

        return min;
    }

    private int getMinChildIndex(int i) {
        if (2 * i + 1 >= current) {
            return 2 * i;
        }

        int left = op[2 * i];
        int right = op[2 * i + 1];

        int minChild = left < right ? 2 * i : 2 * i + 1;
        return minChild;
    }

    private boolean isLeafNode(int i) {
        // TODO Auto-generated method stub
        return 2 * i >= current;
    }

    private void swapDataAtIndex(int current, int root) {
        int i = op[current];
        op[current] = op[root];
        op[root] = i;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 9, 20, 3, 7, 10, 98, 1 };
        MinHeap heap = new MinHeap(arr.length);
        heap.createHeap(arr);
        for (int op : heap.op) {
            System.out.print(op + " ");
        }
        System.out.println("extract min");
        int kthMin = heap.extractMin(7);
        System.out.println("kth min = " + kthMin);
    }

}
