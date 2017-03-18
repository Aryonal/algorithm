package com.aryon.realize;

import java.io.File;

/**
 * Created by Dai on 2016/12/9.
 *
 * 有向图由顶点和边组成，
 * 有向图有可达性问题
 *         有向环检测问题
 *         无环图的拓扑排序问题
 *         强连通性概念与强连通性问题
 */
public class DiGraph extends basicGraph {
    
//    protected final int       v;
//    protected int             e;
//    protected Bag<Integer>[]  adj;
    
    public DiGraph(int v) {
        super(v);
    }
    
    public DiGraph(File f) {
        super(f);
    }
    
    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        e++;
    }
    
    public DiGraph reverse() {
        DiGraph dg = new DiGraph(v);
        for (int i = 0; i < v; i++)
            for (int j : adj[i])
                dg.addEdge(j, i);
        return dg;
    }
}
