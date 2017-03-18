package com.aryon;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Created by Dai on 2016/11/14.
 */

public class mStack<T> implements Iterable<T> {

    private Node<T> first;
    private int length;

    public mStack() {
        this.first = null;
        length = 0;
    }
    public mStack(T item) {
        this.first = new Node<T>(item);
        length = 1;
    }

    public void push(T item) {
        Node<T> n = new Node<>(item);
        n.next = first;
        first = n;
        n = null;
        length++;
    }

    public T pop() {
        if(first == null) {
            throw new EmptyStackException();
        }

        T item = first.item;
        Node<T> n = first;
        first = first.next;
        n = null;
        length--;
        return item;
    }

    public int getLength() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new mIterator();
    }

    private class mIterator implements Iterator<T> {

        private mStack.Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = (T)current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {}
    }

    private class Node<T> {
        private T item;
        private Node next;

        private Node() {
            this.item = null;
            this.next = null;
        }
        private Node(T item) {
            this.item = item;
            this.next = null;
        }

        private void setItem(T item) {
            this.item = item;
        }

        private void setNext(T item) {
            this.next = new Node<T>(item);
        }

    }

}
