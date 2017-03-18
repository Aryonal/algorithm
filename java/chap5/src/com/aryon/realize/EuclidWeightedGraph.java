package com.aryon.realize;

import com.aryon.prototype.Edge;
import com.aryon.prototype.ReadFromFile;
import edu.princeton.cs.algs4.Bag;

import java.io.File;

/**
 * Created by Dai on 2016/12/12.
 */
public class EuclidWeightedGraph {
    private int             v;
    private int             e;
    private Bag<Edge>[]     adj;
    
    public int v() {
        return v;
    }
    public int e() {
        return e;
    }
    
    public EuclidWeightedGraph(int v) {
        this.v = v;
        this.e = 0;
        this.adj = (Bag<Edge>[])new Bag[v];
        for (int i = 0; i< v; i++)
            adj[i] = new Bag<>();
    }
    public EuclidWeightedGraph(File f) {
        ReadFromFile rf = new ReadFromFile(f);
        this.v = rf.nextInt();
        this.e = rf.nextInt();
        this.adj = (Bag<Edge>[])new Bag[v];
        for (int i = 0; i< v; i++)
            adj[i] = new Bag<>();
        
        int t = rf.nextInt();
        while (t != -1) {
            Edge e = new Edge(t, rf.nextInt(), rf.nextDouble());
            addEdge(e);
            t = rf.nextInt();
        }
    }
    public void addEdge(Edge e) {
        adj[e.v].add(e);
        adj[e.w].add(e);
        this.e++;
    }
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int i = 0; i < v; i++)
            for(Edge e : adj[i])
                if (e.other(v) > v) b.add(e);
        return b;
    }
    
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < v; i++) {
            s.append(i).append(":");
            for (Edge e : adj[i])
                s.append(e.toString()).append(",");
            s.append("\n");
        }
        return s.toString();
    }
}
