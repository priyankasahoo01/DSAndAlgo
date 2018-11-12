package com.linkedlist;

public interface LinkedList<T> {
    public void addAtFirst(T data);

    public T deleteFirst();

    void print();

    void addAtEnd(T data);

    T deleteAtEnd();

    T findMiddleElement();

    T findNthNodeFromEnd(int n);
}
