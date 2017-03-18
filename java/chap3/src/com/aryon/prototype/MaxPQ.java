package com.aryon.prototype;

/**
 * Created by Dai on 2016/11/22.
 */
public interface MaxPQ<Key extends Comparable<Key>> {

    void Insert(Key k);
    Key getMax();
    Key delMax();
    boolean isEmpty();
    int size();

    void print(int a, int b);
}
