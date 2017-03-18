package com.aryon.realize;

import com.aryon.prototype.AbstractGraph;
import com.aryon.prototype.AbstractGraphPath;
import edu.princeton.cs.algs4.Queue;

/**
 * Created by Dai on 2016/12/7.
 */
public class BreadthFirstPath extends AbstractGraphPath {

    private Queue<Integer>  q;
    
    public BreadthFirstPath() {
        super();
    }
    
    public BreadthFirstPath(AbstractGraph g, int s) {
        super(g, s);
        q = new Queue<>();
        bfp(g, source);
    }
    
    private void bfp(AbstractGraph g, int v) {
        for (int w : g.adj(v))
            if (!marked[w]) {
                marked[w] = true;
                q.enqueue(w);
                edge[w] = v;
            }
        if (q.isEmpty()) return;
        bfp(g, q.dequeue());
    }
}
