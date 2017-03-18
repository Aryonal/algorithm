package com.aryon.prototype;

/**
 * Created by Dai on 2016/12/5.
 */
public abstract class AbstractGraphSearch {
    
    protected boolean[]   marked;
    protected int         count;
    protected int         source;
    
    public AbstractGraphSearch() {}
    public AbstractGraphSearch(AbstractGraph g, int s) {
        source = s;
        marked = new boolean[g.v()];
        count = 0;
    }
    
    public boolean marked(int v) {
        return marked[v];
    }
    public int count() {
        return count;
    }
    
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(">>>" + this.getClass().getSimpleName() + ":\n");
        s.append(source).append(':');
        for (int i = 0; i < marked.length; i++)
            if (marked[i]) s.append(i).append(' ');
        return s.toString();
    }
}
