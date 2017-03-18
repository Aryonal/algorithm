package com.aryon.realize;

import com.aryon.prototype.ColorNode;
import com.aryon.prototype.ST;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

/**
 * Created by Dai on 2016/11/30.
 */
public class RedBlackBST<K extends Comparable<K>, V> extends BinarySortTree<K,V> implements ST<K,V> {
    
    private final static boolean        BLACK = false;
    private final static boolean        RED = true;
    
    private int                         size;
    private ColorNode<K,V>              root;
    private enum                        flag { min, max, left, right };
    
    public                              RedBlackBST() {
        
        this.size = 0;
        this.root = null;
    }
    
    private boolean                     isRed(ColorNode<K,V> n) {
        
        if (n == null) return BLACK;
        return n.color();
    }
    private ColorNode<K,V>              rotate(ColorNode<K,V> n) {
        
        if      (isRed(n.right())) return rotate(n, flag.left);
        else if (isRed(n.left())) return rotate(n,flag.right);
        return null;
    }
    private ColorNode<K,V>              rotate(ColorNode<K,V> n, flag f) {
        
        if (f == flag.left) {
            ColorNode<K,V> x = n.right();
            n.setRight(x.left()); x.setLeft(n);
            return x;
        } else if (f == flag.right) {
            ColorNode<K,V> x = n.left();
            n.setLeft(x.right()); x.setRight(n);
            return x;
        }
        return null;
    }
    private ColorNode<K,V>              traverse(ColorNode<K,V> r, K k, V v) {
        
        if (r == null) {
            if (v == null) return null;
            size ++;
            return new ColorNode<K, V>(k, v, RED);
        }
    
        /*if (isRed(r.left()) && isRed(r.right())) {
            r.left().toggle();
            r.right().toggle();
            r.toggle();
        }*/ // be here to realize 2-3-4 node tree
        
        int cmp = r.key().compareTo(k);
        
        if      (cmp > 0)   if (v == null)  return traverse(r.left(), k, null);
                            else            r.setLeft(traverse(r.left(), k, v));
                            
        else if (cmp < 0)   if (v == null)  return traverse(r.right(), k, null);
                            else            r.setRight((traverse(r.right(), k, v)));
                            
        else                if (v == null)  return r;
                            else            r.setValue(v);
        
        if (isRed(r.right()) && !isRed(r.left()) || isRed(r.left()) && isRed(r.left().left())) r = rotate(r);
        if (isRed(r.left()) && isRed(r.right())) {
            r.left().toggle();
            r.right().toggle();
            r.toggle();
        }
        
        return r;
    }
    
    @Override
    public V get(K key) {
        return traverse(root, key, null).value();
    }
    
    @Override
    public void put(K key, V value) {
        root = traverse(root, key, value);
    }
    
    @Override // TODO delete(k)
    public void delete(K key) {
        
    }
    
    @Override // TODO deleteMin
    public void deleteMin() {
        
    }
    
    @Override // TODO deleteMax
    public void deleteMax() {
        
    }
    
    @Override // TODO contains(k)
    public boolean contains(K key) {
        return false;
    }
    
    @Override // TODO isEmpty
    public boolean isEmpty() {
        return false;
    }
    
    @Override // TODO size
    public int size() {
        return 0;
    }
    
    @Override //TODO size(lo,hi)
    public int size(K lo, K hi) {
        return 0;
    }
    
    @Override // TODO rank(k)
    public int rank(K key) {
        return 0;
    }
    
    @Override // TODO min
    public K min() {
        return null;
    }
    
    @Override // TODO max
    public K max() {
        return null;
    }
    
    @Override // TODO floor(k)
    public K floor(K key) {
        return null;
    }
    
    @Override // TODO ceiling(k)
    public K ceiling(K key) {
        return null;
    }
    
    @Override // TODO select(rank)
    public K select(int rank) {
        return null;
    }
    
    @Override // TODO Iterable Keys
    public Iterable<K> Keys() {
        return null;
    }
    
    @Override // TODO Iterable Keys(lo,hi)
    public Iterable<K> Keys(K lo, K hi) {
        return null;
    }
    
    @Override
    public void print() {
        StdOut.println("Size: " + size);
        super.print(root);
        StdOut.println();
    }
}
