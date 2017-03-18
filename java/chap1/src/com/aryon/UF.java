package com.aryon;

/**
 * Created by Dai on 2016/11/17.
 */
public interface UF {
    void union(int p, int q);
    int find(int p);
    boolean connected(int p, int q);
    int getCount();
    int[] getId();
}
