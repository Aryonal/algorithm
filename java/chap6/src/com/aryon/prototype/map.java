package com.aryon.prototype;

/**
 * Created by Dai on 2017/1/21.
 */
public interface map <K extends Comparable<K>, V> extends Iterable<K> {
    
    int  size();
    void add(K k, V v);
    V    get(K k);
    V    del(K k);
    V    delMin();
    V    delMax();
    boolean has(K k);
    boolean isEmpty();
    
    K    rand();
    K    min();
    K    max();
    
    K    ceil(K k);
    K    floor(K k);
}
