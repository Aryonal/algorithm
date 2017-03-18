package com.aryon.prototype;

/**
 * Created by Dai on 2016/11/28.
 */
public class Node<K extends Comparable<K>, V> {
    
    private K               item;
    private V               value;
    protected Node<K, V>    left;
    protected Node<K, V>    right;
    
    public                  Node() {
        this(null, null, null, null);
    }
    public                  Node(K item, V value) {
        this(item, value, null, null);
    }
    public                  Node(K item, V value, Node<K,V> left, Node<K,V> right) {
        this.item = item;
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
    public K                key() {
        return this.item;
    }
    public V                value() {
        return this.value;
    }
    public Node<K, V>       left() {
        return this.left;
    }
    public Node<K, V>       right() {
        return this.right;
    }
    public void             setKey(K item) {
        this.item = item;
    }
    public void             setValue(V value) {
        this.value = value;
    }
    public void             setLeft(Node<K, V> left) {
        this.left = left;
    }
    public void             setRight(Node<K, V> right) {
        this.right = right;
    }
    
    @Override
    public String           toString() {
        return item.toString();
    }
}
