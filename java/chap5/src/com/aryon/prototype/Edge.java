package com.aryon.prototype;

/**
 * Created by Dai on 2016/12/12.
 */
public class Edge implements Comparable<Edge> {
    public int v;
    public int w;
    public double weight;
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public int other(int v) {
        if (v != this.v && v != this.w) throw new RuntimeException("error input.");
        return this.v == v ? w : v;
    }
    @Override
    public int compareTo(Edge e) {
        return this.weight > e.weight ? +1 :
               this.weight < e.weight ? -1 :
               0;
    }
    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
