package com.linkedlist;

public class SingleLinkedList<T> implements LinkedList<T> {
    Node head;

    class Node {
        T data;
        Node next;

        public Node(T data) {
            super();
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    @Override
    public T findMiddleElement() {
        if (head != null) {
            Node one = head, two = head.next;
            while (two != null && two.next != null) {
                one = one.next;
                two = two.next.next;
            }
            return one.data;
        }
        return null;
    }

    @Override
    public T findNthNodeFromEnd(int n) {
        if (head != null) {
            Node curr = head;
            Node fast = head;
            int counter = n;
            while (--counter > 0) {

                fast = fast.next;
                if (fast == null) {
                    return null;
                }
            }

            while (fast != null && fast.next != null) {
                curr = curr.next;
                fast = fast.next;
            }
            return curr.data;
        }
        return null;
    }

    @Override
    public void addAtFirst(T data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    @Override
    public void addAtEnd(T data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;

        }
    }

    @Override
    public T deleteFirst() {
        if (head != null) {
            Node temp = head;
            head = head.next;
            temp.next = null;
            return temp.data;
        }
        return null;
    }

    @Override
    public T deleteAtEnd() {
        if (head != null) {
            Node curr = head;
            Node prev = null;
            while (curr.next != null) {
                prev = curr;
                curr = curr.next;
            }
            prev.next = null;
            return prev.data;
        }
        return null;
    }

    @Override
    public void print() {
        System.out.print("Print --> ");
        if (head != null) {
            Node curr = head;
            System.out.print(curr.data + " \t");
            while (curr.next != null) {
                System.out.print(curr.next.data + "\t");
                curr = curr.next;
            }
        }
    }

}
