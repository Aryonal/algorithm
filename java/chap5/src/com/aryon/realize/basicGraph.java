package com.aryon.realize;

import com.aryon.prototype.AbstractGraph;
import com.aryon.prototype.ReadFromFile;
import edu.princeton.cs.algs4.Bag;

import java.io.File;

/**
 * Created by Dai on 2016/12/5.
 */
public class basicGraph extends AbstractGraph {
    
    protected final int       v;
    protected int             e;
    protected Bag<Integer>[]  adj;
    
    public basicGraph(int v) {
        super(v);
        this.v = v;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++)
            adj[i] = new Bag<>();
    }
    
    public basicGraph(File f) {
        super(f);
        ReadFromFile rf = new ReadFromFile(f);
        this.v = rf.nextInt();
        this.e = rf.nextInt();
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++)
            adj[i] = new Bag<>();
        
        int tmp = rf.nextInt();
        do {
            this.addEdge(tmp, rf.nextInt());
            tmp = rf.nextInt();
        } while (tmp != -1);
    }
    
    @Override
    public int v() {
        return v;
    }
    
    @Override
    public int e() {
        return e;
    }
    
    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        e++;
    }
    
    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
    @Override
    public int degree(int v) {
        return adj[v].size();
    }
    
    @Override
    public int maxDegree() {
        return 0;
    }
    
    @Override
    public int avgDegree() {
        return 0;
    }
    
    @Override
    public int loops() {
        return 0;
    }
    
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(">>>" + this.getClass().getSimpleName() + ":\n");
        for (int i = 0; i < v; i++) {
            s.append(i).append(':');
            for (int w : adj[i])
                s.append(w).append(' ');
            s.append('\n');
        }
        return s.toString();
    }
}
