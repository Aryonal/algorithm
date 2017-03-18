package com.aryon.realization;

import com.aryon.prototype.map;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Dai on 2017/1/21.
 */

public class RedBlackTree <K extends Comparable<K>, V> implements map<K,V> {
    private static boolean  RED = true;
    private static boolean  BLACK = false;
    private static boolean  LEFT = true;
    private static boolean  RIGHT = false;
    private node<K,V>       root;
    private node<K,V>       iter_p;
    private node<K,V>       deln;
    
    private class node <K extends Comparable<K>, V> {
        K           key;
        V           value;
        int         size;
        boolean     color;
        node<K,V>   left;
        node<K,V>   right;
        node<K,V>   next;
        node<K,V>   back;
        node(K k, V v) {
            key = k; value = v;
            size = 1; color = RED;
            left = right = next = back = null;
        }
        node<K,V> get(boolean dir) {
            return dir == LEFT ? left : right;
        }
        node<K,V> get(boolean dir1, boolean dir2) {
            return dir2 == LEFT ? get(dir1).left : get(dir1).right;
        }
        node<K,V> getl(boolean dir) {
            return dir == LEFT ? next : back;
        }
        void set(node<K,V> n, boolean dir) {
            if(dir == LEFT) left = n;
            else            right = n;
        }
        void setl(node<K,V> n, boolean dir) {
            if(dir == LEFT) next = n;
            else            back = n;
        }
    }

    public RedBlackTree() {
        root = iter_p = deln = null;
    }
    
    private boolean red(node<K,V> n) {
        return n != null && n.color == RED;
    }
    private boolean single(node<K,V> n) {
        return n != null && !red(n) && !red(n.left) && !red(n.right);
    }
    private int size(node<K,V> n) {
        return n == null ? 0 : n.size;
    }
    public int size() {
        return size(root);
    }
    
    // get key
    private node<K,V> _minNode() {
        if(root == null) return null;
        node<K,V> r = root;
        while(r.left != null)
            r = r.left;
        return r;
    }
    private node<K,V> _maxNode() {
        if(root == null) return null;
        node<K,V> r = root;
        while(r.right != null)
            r = r.right;
        return r;
    }
    public K rand() {
        if(root == null) return null;
        int rd = (new Random().nextInt()) % size();
        node<K,V> n = _minNode();
        for(int i = 0; i < rd; i++) {
            n = n.next;
        }
        return n.key;
    }
    public K min() {
        return _minNode() == null ? null : _minNode().key;
    }
    public K max() {
        return _maxNode() == null ? null : _maxNode().key;
    }
    public K ceil(K k) {
        node<K,V> n = _get(root, k);
        if(n == null) return null;
        return n.next.key;
    }
    public K floor(K k) {
        node<K,V> n = _get(root, k);
        if(n == null) return null;
        return n.back.key;
    }
    
    // get value by key
    private node<K,V> _get(node<K,V> r, K k) {
        if(r == null) return null;
        int cmp = k.compareTo(r.key);
        if(cmp == 0) return r;
        if(cmp > 0)  return _get(r.right, k);
        else         return _get(r.left, k);
    }
    public V get(K k) {
        return _get(root, k).value;
    }
    
    // add key and value
    private void flipcolor(node<K,V> n, boolean color) {
        n.color = color;
        n.left.color = n.right.color = !color;
    }
    private node<K,V> rotate(node<K,V> n, boolean dir) {
        node<K,V> p;
        p = n.get(!dir);
        n.set(p.get(dir), !dir);    p.set(n, dir);
        p.color = n.color;          n.color = RED;
        p.size = n.size;            n.size = size(n.left) + size(n.right) + 1;
        return p;
    }
    private node<K,V> fixup(node<K,V> n) {
        boolean dir;
        if(red(n.get(LEFT))) dir = LEFT;
        else if(red(n.get(RIGHT))) dir = RIGHT;
        else return n;
        if(red(n.get(dir,!dir)))
            n.set(rotate(n.get(dir), dir), dir);
        if(red(n.get(dir,dir))) {
            n = rotate(n, !dir);
        }
        return n;
    }
    private node<K,V> _add(node<K,V> r, node<K,V> p, boolean dir, K k, V v) {
        if(r == null) {
            node<K,V> n = new node<>(k, v);
            if(p == null) return n;
            if(p.getl(!dir) != null) {
                p.getl(!dir).setl(n, dir);
                n.setl(p.getl(!dir), !dir);
            }
            n.setl(p, dir);
            p.setl(n, !dir);
            return n;
        }
        if(red(r.left) && red(r.right)) flipcolor(r, RED);
        int cmp = k.compareTo(r.key);
        if(cmp == 0) r.value = v;
        else if(cmp > 0)  r.right = _add(r.right, r, RIGHT, k, v);
        else              r.left  = _add(r.left, r, LEFT,  k, v);
        r = fixup(r);
        r.size = size(r.left) + size(r.right) + 1;
        return r;
    }
    public void add(K k, V v) {
        root = _add(root, root, LEFT,  k, v);
        root.color = BLACK;
    }
    
    // del key and value by key
    private void swap(node<K,V> p, node<K,V> q) {
        // swap key and value of p and q
        K ktmp = p.key;
        V vtmp = p.value;
        p.key = q.key;
        p.value = q.value;
        q.key = ktmp;
        q.value = vtmp;
    }
    private void listDel(node<K,V> n) {
        if(n.next != null) n.next.back = n.back;
        if(n.back != null) n.back.next = n.next;
    }
    private node<K,V> fixd(node<K,V> n, boolean dir) {
        if(!single(n.get(dir))) return n;
        if(single(n.get(!dir))) flipcolor(n, BLACK);
        else if(red(n.get(!dir))) {
            n = rotate(n, dir);
        } else {
            if(red(n.get(!dir,dir)))
                n.set(rotate(n.get(!dir), !dir),!dir);
            if(red(n.get(!dir,!dir))) {
                n = rotate(n, dir);
                n.get(dir).color = n.get(!dir).color = BLACK;
                n.get(dir,dir).color = RED;
            }
        }
        return n;
    }
    private node<K,V> _delM(node<K,V> r, boolean dir) {
        if(r == null) return r;
        if(r.get(dir) == null) {
            listDel(r);
            deln = r;
            if(r.get(!dir) != null) r.get(!dir).color = r.color;
            return r.get(!dir);
        }
        r = fixd(r, dir);
        r.set(_delM(r.get(dir), dir),dir);
        r.size = size(r.get(dir)) + size(r.get(!dir)) + 1;
        return r;
    }
    public V delMin() {
        if(root == null) return null;
        root = _delM(root, LEFT);
        return deln.value;
    }
    public V delMax() {
        if(root == null) return null;
        root = _delM(root, RIGHT);
        return deln.value;
    }
    private node<K,V> _del(node<K,V> r, K k) {
        if(r == null) return null;
        int cmp = k.compareTo(r.key);
        if(cmp == 0) {
            if(r.left != null && r.right != null) {
                r = fixd(r, RIGHT);
                r.right = _delM(r.right, LEFT);
                swap(r, deln);
            } else {
                boolean dir = r.right == null ? LEFT : RIGHT;
                listDel(r);
                deln = r;
                if(r.get(dir) != null) r.get(dir).color = r.color;
                return r.get(dir);
            }
        } else {
            boolean dir = cmp < 0 ? LEFT : RIGHT;
            r = fixd(r, dir);
            r.set(_del(r.get(dir), k), dir);
        }
        r.size = size(r.left) + size(r.right) + 1;
        return r;
    }
    public V del(K k) {
        if(root == null) return null;
        root = _del(root, k);
        return deln.value;
    }
    
    // has
    public boolean has(K k) {
        return _get(root, k) != null;
    }
    public boolean isEmpty() {
        return root.size == 0;
    }
    
    @Override
    public Iterator<K> iterator() {
        iter_p = _minNode();
        return new Iterator<K>() {
            @Override
            public boolean hasNext() {
                return iter_p != null;
            }
            @Override
            public K next() {
                K k = iter_p.key;
                iter_p = iter_p.next;
                return k;
            }
        };
    }
}