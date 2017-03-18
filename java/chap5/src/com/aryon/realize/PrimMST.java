package com.aryon.realize;

import com.aryon.prototype.Edge;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * Created by Dai on 2016/12/15.
 */
public class PrimMST {
    private boolean[]       marked; //if has been visited or not
    private Queue<Edge>     mst;    //minimum spanning tree
    private MinPQ<Edge>     pq;     //priority queue of crossing edges
    
    private double[]        dist;   //for instant Prim, minimum distance of each point to mst
    
    public PrimMST(EuclidWeightedGraph g) {
        marked = new boolean[g.v()];
        pq = new MinPQ<>();
        mst = new Queue<>();
        for (Edge e : g.adj(0))
            pq.insert(e);
        /*
         * <p>init dist for instant prim </p>
        dist = new double[g.v()];
        for (int i = 0; i < g.v(); i++)
            dist[i] = Double.MAX_VALUE;
        marked[0] = true;
         */
        visit(g);
    }
    private void visit(EuclidWeightedGraph g) {
        if (!pq.isEmpty()) return;                               //return when there's no crossing edges
        Edge e = pq.delMin();
        int w = marked[e.v] ? e.w : e.v;                        //w is the new point added into mst
        marked[w] = true;
        for (Edge ee : g.adj(w)) {
            if (!marked[ee.other(w)])
            /*
             * <p>each point has only one crossing edge between mst and self</p>
                if (ee.weight < dist[ee.other(w)]) {
                    dist[ee.other(w)] = ee.weight;
                    */
                    pq.insert(ee);
                //}
        }
        mst.enqueue(e);
        visit(g);
    }
    public Queue<Edge> edges() {
        return mst;
    }
    public double weight() {
        return 0;
    }
}
