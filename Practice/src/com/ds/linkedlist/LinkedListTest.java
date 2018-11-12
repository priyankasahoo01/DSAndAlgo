package com.ds.linkedlist;

public class LinkedListTest {
    
    public static void main(String[] args) {
        testSinglyLinkedList();
        testDoublyLinkedList();
    }

    private static void testDoublyLinkedList() {
        System.out.println("testDoublyLinkedList -- start");
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.traverse();
        System.out.println("middle -> "+linkedList.getMiddleNode());
        
        linkedList.addAtFirst("second");
        linkedList.traverse();
        System.out.println("middle -> "+linkedList.getMiddleNode());
        
        linkedList.addAtFirst("first");
        linkedList.traverse();
        System.out.println("middle -> "+linkedList.getMiddleNode());
        
        linkedList.addAtTail("third");
        linkedList.traverse();
        System.out.println("middle -> "+linkedList.getMiddleNode());
        
        linkedList.addAtFirst("zero");
        linkedList.traverse();
        System.out.println("middle -> "+linkedList.getMiddleNode());
        
        linkedList.addAtTail("four");
        linkedList.traverse();
        System.out.println("middle -> "+linkedList.getMiddleNode());
        System.out.println("testDoublyLinkedList -- end");
    }

    private static void testSinglyLinkedList() {
        System.out.println("testSinglyLinkedList -- start");
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.addAtFirst("first");
        linkedList.addAtFirst("second");
        linkedList.addAtFirst("third");
        linkedList.deleteFirst();
        linkedList.addAtFirst("fourth");
        linkedList.traverse();
        System.out.println("second traverse");
        linkedList.traverse();
        System.out.println("testSinglyLinkedList -- end");
    }

}
