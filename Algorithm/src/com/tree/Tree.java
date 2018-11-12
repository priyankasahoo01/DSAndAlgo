package com.tree;

public interface Tree<T> {

    public void add(T data);
    public int height();
    public int level();
    public void print();
    
}
