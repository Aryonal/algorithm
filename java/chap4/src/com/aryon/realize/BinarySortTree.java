package com.aryon.realize;

import com.aryon.prototype.Node;
import com.aryon.prototype.ST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Dai on 2016/11/28.
 */
public class BinarySortTree<K extends Comparable<K>, V> implements ST<K,V> {
    
    private Node<K,V>           root;
    private int                 size;
    private enum                flag {
                                        min,
                                        max
    }
    
    public                      BinarySortTree() {
        this(null);
    }
    public                      BinarySortTree(Node<K,V> n) {
        this.root = n;
        this.size = n == null ? 0 : 1;
    }
    
    private Node<K,V>           trace(Node<K,V> r, K k, V v) {
        if (r == null) {
            size++;
            return new Node<>(k, v);
        }
        int cmp = k.compareTo(r.key());
        if ( cmp > 0)
            r.setRight(trace(r.right(), k, v));
        else if ( cmp < 0)
            r.setLeft(trace(r.left(), k, v));
        else
            r.setValue(v);
        return r;
    }
    private Node<K,V>           trace(Node<K,V> r, K k) {
        if (r == null) return null;
        int cmp = k.compareTo(r.key());
        if ( cmp > 0) return trace(r.right(), k);
        if ( cmp < 0) return trace(r.left(), k);
        return r;
    }
    private Node<K,V>           traceM(Node<K,V> r, flag f) {
        if (r == null) return null;
        if (f == flag.min) {
            if (r.left() == null) return r;
            return traceM(r.left(), f);
        }
        if (f == flag.max) {
            if (r.right() == null) return r;
            return traceM(r.right(), f);
        }
        return null;
    }
    private Node<K,V>           delete(Node<K,V> r, K k) {
        if (r == null) return null;
        int cmp = r.key().compareTo(k);
        if      (cmp < 0)   r.setRight(delete(r.right(), k));
        else if (cmp > 0)   r.setLeft(delete(r.left(), k));
        else {
            if (r.left() == null) return r.right();
            if (r.right() == null) return r.left();
            
            Node<K,V> t = traceM(r.right(), flag.min);
            r.setRight(deleteM(r.right(), flag.min));
            t.setLeft(r.left());
            t.setRight(r.right());
            return t;
        }
        return r;
    }
    private Node<K,V>           deleteM(Node<K,V> r, flag f) {
        if (r == null) return null;
        if (f == flag.min) {
            if (r.left() == null) return r.right();
            r.setLeft(deleteM(r.left(), f));
            return r;
        }
        else {
            if (r.right() == null) return r.left();
            r.setLeft(deleteM(r.right(), f));
            return r;
        }
    }
    private Node<K,V>           select(Node<K,V> r, int rank) {
        if (r == null) return null;
        int rk = size(r.left()) + 1;
        if (rank < rk) return select(r.left(), rank);
        if (rank > rk) return select(r.right(), rank - rk);
        return r;
        
    }
    private int                 rank(Node<K,V> r, K k) {
        if (r == null) return 0;
        int cmp = r.key().compareTo(k);
        if (cmp <= 0) return size(r.left()) + 1 + rank(r.right(), k);
        else return rank(r.left(), k);
    }
    
    protected int               size(Node<K,V> r) {
        if (r == null) return 0;
        if (r.left() == null)
            if (r.right() == null) return 1;
            else return size(r.right()) + 1;
        else
        if (r.right() == null) return size(r.left()) + 1;
        else return size(r.left()) + size(r.right()) + 1;
    }
    protected int               size(Node<K,V> r, K lo, K hi) {
        if (r == null) return 0;
        if (r.left() == null || r.left().key().compareTo(lo)<=0)
            if (r.right() == null || r.right().key().compareTo(hi)>=0) return 1;
            else return size(r.right(), lo, hi) + 1;
        else
        if (r.right() == null || r.right().key().compareTo(hi)>=0) return size(r.left(), lo, hi) + 1;
        else return size(r.left(), lo, hi) + size(r.right(), lo, hi) + 1;
    }
    protected void              add(ArrayList<K> arr, Node<K,V> r, K lo, K hi, flag f) {
        if (r == null) return;
        if (f == flag.min) {
            if (r.key().compareTo(lo) >= 0) add(arr, r.left(), lo, hi, f);
            if (r.key().compareTo(lo) >= 0 && r.key().compareTo(hi) <= 0) arr.add(r.key());
            if (r.key().compareTo(hi) <= 0) add(arr, r.right(), lo, hi, f);
        }
        if (f == flag.max) {
            if (r.key().compareTo(lo) >= 0) add(arr, r.left(), lo, hi, f);
            if (r.key().compareTo(lo) >= 0 && r.key().compareTo(hi) <= 0) arr.add(r.key());
            if (r.key().compareTo(hi) <= 0) add(arr, r.right(), lo, hi, f);
        }
    }
    protected void              print(Node<K,V> r) {
        if(r == null) return;
        print(r.left());
        StdOut.print(r.key()+" ");
        print(r.right());
    }
    
    @Override
    public void print() {
        StdOut.println("Size: " + size);
        print(root);
        StdOut.println();
    }
    
    @Override
    public V get(K key) {
        Node<K,V> n = trace(root, key);
        return n == null ?
                null:
                n.value();
    }
    
    @Override
    public void put(K key, V value) {
        StdOut.print("\nput " + key +"-"+ value + ": \n");
        root = trace(root, key, value);
    }
    
    @Override
    public void delete(K key) {
        StdOut.print("\ndelete "+key+": \n");
        root = delete(root, key);
        size = size(root);
    }
    
    @Override
    public void deleteMin() {
        StdOut.print("\ndeleteMin: \n");
        root = deleteM(root, flag.min);
        size = size(root);
    }
    
    @Override
    public void deleteMax() {
        StdOut.print("\ndeleteMin: \n");
        root = deleteM(root, flag.max);
        size = size(root);
    }
    
    @Override
    public boolean contains(K key) {
        return trace(root, key) != null;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public int size(K lo, K hi) {
        return size(root, lo, hi);
    }
    
    @Override
    public int rank(K key) {
        return rank(root, key);
    }
    
    @Override
    public K min() {
        return traceM(root, flag.min).key();
    }
    
    @Override
    public K max() {
        return traceM(root, flag.max).key();
    }
    
    @Override
    public K floor(K key) {
        return null;
    }
    
    @Override
    public K ceiling(K key) {
        return null;
    }
    
    @Override
    public K select(int rank) {
        return select(root, rank) == null? null : select(root, rank).key();
    }
    
    @Override
    public Iterable<K> Keys() {
        ArrayList<K> arr = new ArrayList<>();
        add(arr,root, min(), max(), flag.min);
        return arr;
    }
    
    @Override
    public Iterable<K> Keys(K lo, K hi) {
        ArrayList<K> arr = new ArrayList<>();
        add(arr,root, lo, hi, flag.min);
        return arr;
    }
    
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            @Override
            public boolean hasNext() {
                return false;
            }
    
            @Override
            public K next() {
                return null;
            }
        };
    }
    
    @Override
    public void forEach(Consumer<? super K> action) {
    }
    
    @Override
    public Spliterator<K> spliterator() {
        return null;
    }
}
