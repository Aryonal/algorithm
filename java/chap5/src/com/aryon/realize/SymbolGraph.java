package com.aryon.realize;

import com.aryon.prototype.ReadFromFile;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dai on 2016/12/7.
 */
public class SymbolGraph {
    
    /**
     * @param st    符号表，用于存储字符串与其索引;
     * @param keys  反向索引，用于通过索引查找字符串;
     * @param g     无向图;
     *
     *        g.v() == st.size() == keys.length
     *
     */
    
    private ST<String, Integer> st;
    private String[]            keys;
    private boolean[]           isMovie;
    private basicGraph          g;
    
    public SymbolGraph() {
    }
    
    public SymbolGraph(int v) {
        g = new basicGraph(v);
        st = new ST<>();
        keys = new String[v];
        isMovie = new boolean[v];
    }
    
    public SymbolGraph(File f) {
        st = new ST<>();
        ReadFromFile rf = new ReadFromFile(f);
        String tmp = rf.nextString('/', '\n', '\r');
        while (tmp != null) {
            if (!st.contains(tmp))
                st.put(tmp, st.size());
            tmp = rf.nextString('/', '\n', '\r');
        }
        rf.close();
        
        keys = new String[st.size()];
        for (String s : st)
            keys[st.get(s)] = s;
        g = new basicGraph(st.size());
        isMovie = new boolean[st.size()];
    
        rf = new ReadFromFile(f);
        tmp = rf.nextString('\n', '\r');
        while (tmp != null) {
            String[] names = tmp.split("/");
            isMovie[st.get(names[0])] = true;
            for (String s : names)
                if (!s.equals(names[0])) g.addEdge(st.get(s), st.get(names[0]));
            tmp = rf.nextString('\n', '\r');
        }
    }
    
    public int v() {
        return g.v();
    }
    
    public int e() {
        return g.e();
    }
    
    public void addEdge(String v, String w) {
        if (!(st.contains(v) && st.contains(w))) return;
        g.addEdge(st.get(v), st.get(w));
    }
    
    public Iterable<String> adj(String v) {
        Queue<String> q = new Queue<>();
        for (int i : g.adj(st.get(v)))
            q.enqueue(v);
        return q;
    }
    
    /*
    public int degree(String v) {
        return 0;
    }
    
    public int maxDegree() {
        return 0;
    }
    
    public int avgDegree() {
        return 0;
    }
    
    public int loops() {
        return 0;
    }*/
    
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(">>>" + this.getClass().getSimpleName() + ":\n");
        for (int i = 0; i < g.v(); i++) {
            if (isMovie[i]) {
                s.append(keys[i]).append(':');
                for (int w : g.adj(i))
                    s.append(keys[w]).append('/');
                s.append('\n');
            }
        }
        return s.toString();
    }
}
