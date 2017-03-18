package com.aryon.realize;

import com.aryon.prototype.AbstractGraph;
import com.aryon.prototype.AbstractGraphSearch;

/**
 * Created by Dai on 2016/12/5.
 */
public class DeepFirstSearch extends AbstractGraphSearch {
    
    //private boolean[]   marked;
    //private int         count;
    //private int         source;
    
    public DeepFirstSearch() {
        super();
    }
    
    public DeepFirstSearch(AbstractGraph g, int s) {
        super(g, s);
        dfs(g, s);
    }
    
    private void dfs(AbstractGraph g, int v) {
        marked[v] = true;
        count++;
        for (int i : g.adj(v))
            if (!marked[i]) dfs(g,i);
    }
}
