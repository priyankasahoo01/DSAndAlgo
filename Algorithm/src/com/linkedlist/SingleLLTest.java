package com.linkedlist;

public class SingleLLTest {
    public static void main(String[] args) {
        LinkedList<Integer> ll = new SingleLinkedList<>();
        ll.addAtFirst(2);
        ll.addAtFirst(3);
        ll.addAtFirst(4);
       
         for(int i=4;i>=0;i--) {
             Integer data = ll.findNthNodeFromEnd(i);
             System.out.println(i+"th node from end is --> "+data);
         }
         
        //4 3   2
        ll.print();
        System.out.println("\t middle --> "+ll.findMiddleElement());
        ll.deleteFirst();
        //3 2
        ll.print();
        System.out.println("\t middle --> "+ll.findMiddleElement());
        ll.addAtEnd(1);
        //3 2   1
        ll.print();
        System.out.println("\t middle --> "+ll.findMiddleElement());
        ll.deleteAtEnd();
        //3 2
        ll.print();
        System.out.println("\t middle --> "+ll.findMiddleElement());
        
    }

}
