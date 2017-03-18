package com.aryon;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Dai on 2016/11/15.
 */
public class quickUnion implements UF{

    private class Node {
        private Node parent;
        private int point;

        public Node(int p) {
            this.setPoint(p);
            this.setParent(null);
        }

        public boolean isRoot() {
            if(this.parent == null) return true;
            return false;
        }

        public Node getRoot() {
            Node n = this;
            while(n.parent != null) {
                n = n.parent;
            }
            return n;
        }

        public boolean equals(Node p) {
            if(this.point == p.point) return true;
            return false;
        }

        public void setPoint(int p) {
            this.point = p;
        }
        public int getPoint() {
            return point;
        }
        public void setParent(Node n) {
            parent = n;
        }
        public Node getParent() {
            return parent;
        }
    }
    private Node[] nodes;
    private int count;

    public quickUnion(int N) {
        setCount(N);
        nodes = new Node[N];
        for(int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }
    }

    private void setCount(int n) {
        count = n;
    }
    public int getCount() {
        return count;
    }
    private void decrease() { count--; }
    public void printNodes() {
        for(Node i : nodes)
            StdOut.print(i.getPoint() + " ");
        StdOut.println();
    }

    private void check(int p) {
        if(p > nodes.length) throw new IndexOutOfBoundsException();
    }

    @Override
    public int[] getId() {
        return new int[0];
    }

    public void union(int p, int q) {
        check(p);
        check(q);

        Node pR = nodes[p].getRoot();
        Node qR = nodes[q].getRoot();
        if(pR.equals(qR)) return;
        decrease();
        pR.setParent(qR);
    }

    public int find(int p) {
        check(p);
        return nodes[p].getRoot().getPoint();
    }

    public boolean connected(int p, int q) {
        return nodes[p].getRoot().equals(nodes[q].getRoot());
    }
}
