package com.aryon;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Dai on 2016/11/17.
 */
public class drawTree {

    private static final int SIZEOFCANVAS = 500;

    private static int rectHeight;
    private static int rectWidth;

    private static class node {
        String name;
        Point center;
        node parent;
        int layer;
        int[] lineToParent;
    }
    private static class Point {
        double x;
        double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static node[] nodes;

    private static void genCoord(int[] id) {
        nodes = new node[id.length];
        int maxLayers = 0;
        int maxPerLayer = 0;
        int[] perLayer;
        for(int i = 0; i < id.length; i++) {
            nodes[i] = new node();
            nodes[i].name = Integer.toString(i);
            nodes[i].layer = 0;
        }
        for(int i = 0; i < id.length; i++) {
            nodes[i].parent = nodes[id[i]];
            if(nodes[i].parent != nodes[i])
                nodes[i].parent.layer = Math.max(nodes[i].parent.layer,nodes[i].layer+1);
        }

        for(node n : nodes) {
            int currentLayers =  n.layer;
            if(currentLayers > maxLayers) maxLayers = currentLayers;
        }
        perLayer = new int[maxLayers+1];
        for(int i = 0; i < maxLayers+1; i++) perLayer[i] = 0;
        for(node n : nodes) {
            perLayer[n.layer]++;
        }
        for(int i :perLayer) maxPerLayer = maxLayers > i ? maxLayers : i;
        //finish init

        //
        rectHeight = SIZEOFCANVAS/2*(maxLayers+1);
        rectWidth = SIZEOFCANVAS/2*(maxPerLayer+1);

    }

    private static void printNodes(node[] nodes) {
        for(node n : nodes) {
            StdOut.println(n.name + ": \n    layer: " + n.layer + "\n    coord: ");
        }
    }

    public static void draw(int[] id) {
        StdDraw.setCanvasSize(SIZEOFCANVAS,SIZEOFCANVAS);
        StdDraw.setXscale(0, SIZEOFCANVAS);
        StdDraw.setYscale(0,SIZEOFCANVAS);
        genCoord(id);
        printNodes(nodes);
    }
}
