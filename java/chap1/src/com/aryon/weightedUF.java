package com.aryon;

/**
 * Created by Dai on 2016/11/17.
 */
public class weightedUF implements UF{
    private int[] id; //parent id
    private int count;
    private int[] size;

    private int getRoot(int p) {
        if(id[p] == p) return p;
        return id[p];
    }

    public weightedUF() {}
    public weightedUF(int N) {
        count = N;
        id = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public int[] getId() {
        return id;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void union(int p, int q) {
        int pR = getRoot(p);
        int qR = getRoot(q);
        if(pR == qR) return;
        if(size[pR] > size[qR]) {
            id[qR] = pR;
            size[pR] = Math.max(size[qR] + 1, size[pR]);
        }
        else {
            id[pR] = qR;
            size[qR] = Math.max(size[pR] + 1, size[qR]);
        }
        count --;
    }

    @Override
    public int find(int p) {
        return getRoot(p);
    }

    @Override
    public boolean connected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }
}
