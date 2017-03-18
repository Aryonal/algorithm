package com.aryon;

/**
 * Created by Dai on 2016/11/19.
 */
public class comparableInteger implements Comparable<comparableInteger> {

    private final int i;

    public comparableInteger(int i) {
        this.i = i;
    }

    public String toString() {
        return Integer.toString(i);
    }

    @Override
    public int compareTo(comparableInteger o) {
        return i - o.i;
    }
}
