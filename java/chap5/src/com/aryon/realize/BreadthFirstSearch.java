package com.aryon.realize;

import com.aryon.prototype.AbstractGraph;
import com.aryon.prototype.AbstractGraphSearch;
import edu.princeton.cs.algs4.Queue;

/**
 * Created by Dai on 2016/12/7.
 */
public class BreadthFirstSearch extends AbstractGraphSearch {
    
    //private int             count;
    //private int             source;
    //private boolean[]       marked;
    private Queue<Integer>  q;
    
    public BreadthFirstSearch() {
        super();
    }
    
    public BreadthFirstSearch(AbstractGraph g, int s) {
        super(g, s);
        q = new Queue<>(); q.enqueue(source);
        bfs(g, q.dequeue());
    }
    
    private void bfs(AbstractGraph g, int v) {
        for (int w : g.adj(v))
            if (!marked[w]) {
                count++;
                q.enqueue(w);
                marked[w] = true;
            }
        if (q.isEmpty()) return;
        bfs(g, q.dequeue());
    }
}
