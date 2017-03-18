package com.aryon.prototype;


import edu.princeton.cs.algs4.Stack;

/**
 * Created by Dai on 2016/12/6.
 */
public abstract class AbstractGraphPath {
    
    protected boolean[]   marked;
    protected int[]       edge;
    protected int         source;
    
    public AbstractGraphPath() {}
    public AbstractGraphPath(AbstractGraph g, int s) {
        marked = new boolean[g.v()];
        edge = new int[g.v()];
        source = s;
    }
    
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) return null;
        Stack<Integer> s = new Stack<>();
        for (int i = v; i != source; i = edge[i])
            s.push(i);
        s.push(source);
        return s;
    }
    
    @Override
    public String toString() {
        Iterable<Integer> it;
        StringBuffer s = new StringBuffer();
        s.append(">>>" + this.getClass().getSimpleName() + ":\n");
        for (int i = 0; i < edge.length; i++) {
            s.append("to"+i+":");
            it = pathTo(i);
            if (it != null) {
                for (int w : it)
                    s.append(w).append('-');
                s.append('\n');
            }
            else {
                s.append("unreachable\n");
            }
        }
        return s.toString();
    }
}
