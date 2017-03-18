package com.aryon.prototype;

import java.io.File;

/**
 * Created by Dai on 2016/12/5.
 */
public abstract class AbstractGraph {
    
    public AbstractGraph() {}
    public AbstractGraph(int v) {}
    public AbstractGraph(File f) {}
    
    public abstract int v();
    public abstract int e();
    public abstract void addEdge(int v, int w);
    public abstract Iterable<Integer> adj(int v);
    
//    public abstract void add(int v);
//    public abstract void delete(int v);
//    public abstract void deleteEdge(int v, int w);
//    public abstract boolean contains(int v, int w);
    
    public abstract int degree(int v);
    public abstract int maxDegree();
    public abstract int avgDegree();
    public abstract int loops();
    
    @Override
    public abstract String toString();
}
