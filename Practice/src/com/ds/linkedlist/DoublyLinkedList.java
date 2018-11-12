package com.ds.linkedlist;

public class DoublyLinkedList {

    Node head;
    Node tail;

    public void addAtFirst(Object value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void addAtTail(Object value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    public Object getMiddleNode() {
        Node local = head;
        Node local1 = head;
        if (head != null) {
            while (local1!=null && local1.next != null) {
                local = local.next;
                local1 = local1.next.next;
            }
        }
        return local;
    }

    public void traverse() {
        Node local = head;
        while (local != null) {
            System.out.print(local + "\t");
            local = local.next;
        }
        System.out.println("\n");
    }

    class Node {
        Object value;
        Node next;

        public Node(Object value) {
            super();
            this.value = value;
        }

        @Override
        public String toString() {
            return "[value=" + value + "]";
        }

    }

}
