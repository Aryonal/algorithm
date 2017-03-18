package com.aryon;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Dai on 2016/11/14.
 */
public class UnionFind implements UF{
    private int[] id;
    private int count;
    public UnionFind() {}
    public UnionFind(int N) {
        setCount(N);
        id = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    public int getCount() {
        return count;
    }
    private void setCount(int n) {
        count = n;
    }
    public void printId() {
        for(int i : id)
            StdOut.print(i + " ");
        StdOut.println();
    }
    private void decrease() { count--; }

    @Override
    public int[] getId() {
        return id;
    }

    public void union(int p, int q) {
            int pID = id[p];
            int qID = id[q];
            if (pID == qID) return;
            decrease();
            for (int i = 0; i < id.length; i++)
                if (id[i] == pID) id[i] = qID;
        }
    public int find(int p) {
            return id[p];
        }
    public boolean connected(int p, int q) {
            return id[p] == id[q];
        }

}
