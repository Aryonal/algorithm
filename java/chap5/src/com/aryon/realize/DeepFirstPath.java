package com.aryon.realize;

import com.aryon.prototype.AbstractGraph;
import com.aryon.prototype.AbstractGraphPath;

/**
 * Created by Dai on 2016/12/6.
 */
public class DeepFirstPath extends AbstractGraphPath {
    
    public DeepFirstPath() {
        super();
    }
    
    public DeepFirstPath(AbstractGraph g, int s) {
        super(g, s);
        dfp(g, s);
    }
    
    private void dfp(AbstractGraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v))
            if (!marked[w]) {
                edge[w] = v;
                dfp(g,w);
            }
    }
}
