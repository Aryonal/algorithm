package com.aryon.prototype;

/**
 * Created by Dai on 2016/11/30.
 */
public class ColorNode<K extends Comparable<K>, V> extends Node<K,V> {
    
    private boolean             color;
    protected ColorNode<K,V>    left;
    protected ColorNode<K,V>    right;
    
    { this.color = false; }
    
    public                      ColorNode() {
        super();
    }
    public                      ColorNode(K key, V value) {
        super(key, value);
    }
    public                      ColorNode(K key, V value, boolean c) {
        super(key, value);
        this.color = c;
    }
    public                      ColorNode(K key, V value, ColorNode<K,V> left, ColorNode<K,V> right) {
        super(key,value,left,right);
    }
    public                      ColorNode(K key, V value, ColorNode<K,V> left, ColorNode<K,V> right, boolean c) {
        super(key,value,left,right);
        this.color = c;
    }
    
    public void                 setColor(boolean c) {
        this.color = c;
    }
    public boolean              color() {
        return this.color;
    }
    public void                 toggle() {
        this.color = !this.color;
    }
    
    @Override
    public ColorNode<K,V>       left() {
        return (ColorNode<K,V>) super.left();
    }
    @Override
    public ColorNode<K,V>       right() {
        return (ColorNode<K,V>) super.right();
    }
}
