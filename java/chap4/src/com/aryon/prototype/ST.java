package com.aryon.prototype;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Dai on 2016/11/27.
 */
public interface ST<K extends Comparable<K>, V> extends Iterable<K> {

    V               get(K key);
    void            put(K key, V value);
    void            delete(K key);
    void            deleteMin();
    void            deleteMax();
    boolean         contains(K key);
    boolean         isEmpty();
    int             size();
    int             size(K lo, K hi);
    int             rank(K key);
    K               min();
    K               max();
    K               floor(K key);
    K               ceiling(K key);
    K               select(int rank);
    Iterable<K>     Keys();
    Iterable<K>     Keys(K lo, K hi);
    
    void            print();


    @Override
    default Iterator<K> iterator() {
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
    default void forEach(Consumer<? super K> action) {

    }

    @Override
    default Spliterator<K> spliterator() {
        return null;
    }
}
