package com.aryon;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Dai on 2016/11/14.
 */
public class Josephus {
    private int N;
    private int cnt;
    Queue<Integer> queue;
    public Josephus(int n, int c) {
        this.N = n;
        this.cnt = c;
        queue = new Queue<>();
        for(int i = 0; i < N; i++) {
            queue.enqueue(i);
        }
    }
    public void getQueue() {
        // print queue
        int i = 1;
        while(!queue.isEmpty()) {
            if (i == cnt) {
                StdOut.print(queue.dequeue()+" ");
                i = 1;
            } else {
                queue.enqueue(queue.dequeue());
                i++;
            }
        }
    }
}
