package com.ds.linkedlist;

public class SinglyLinkedList {
    private Node head;

    public void addAtFirst(Object value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
        }

    }

    public void traverse() {
        Node local = head;
        while (local != null) {
            System.out.println(local);
            local = local.next;
        }
    }

    public void reverse() {
        Node current = head;
        Node prev = null;
        Node next ;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            
        }
    }

    class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return " [value=" + value + "]";
        }

    }

}
