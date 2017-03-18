package com.prototype;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Dai on 2016/11/18.
 */

public class Sorter<T extends Comparable> {

    public static void main(String[] args) {}

    protected T[] a;
    public Sorter(T[] a) {
        if(a.length < 2) throw new IllegalArgumentException("length of a should be more than 2");
        this.a = a;
    }

    public void setA(T[] a) {
        this.a = a;
    }
    protected boolean less(int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }
    protected boolean more(int i, int j) {
        return a[i].compareTo(a[j]) > 0;
    }
    protected void exchange(int i, int j) {
        if(i == j) return;
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
        t = null;
    }

    //use this only if a[] is made up of two sorted sequences with the same length.
    protected void merge(int lo, int hi) {
        merge(lo, (lo+hi)/2, hi);
    }

    protected void merge(int lo, int mid, int hi) {
        if(mid-lo+1 < hi-mid) throw new IllegalArgumentException("mid is too small.");
        if(mid == hi || less(mid,mid+1)) return;

        int i = lo; int j = mid + 1;
        Comparable[] aux = new Comparable[mid+1];
        for(int k = lo; k < mid+1; k++) {
            aux[k] = a[k];
        }
        for(int k = lo; k < hi+1; k++) {
            if(i > mid) {
                a[k] = a[j]; j++;
            }
            else if(j > hi) {
                a[k] = (T)aux[i]; i++;
            }
            else if(aux[i].compareTo(a[j]) < 0) {
                a[k] = (T)aux[i]; i++;
            }
            else if(aux[i].compareTo(a[j]) >= 0) {
                a[k] = a[j]; j++;
            }
        }
    }

    public void show() {
        show(0, a.length);
    }
    public void show(int i, int j) {
        if(i > 0) StdOut.print("... ");
        for(int c = i; c < j; c++) {
            StdOut.print(a[c].toString() + " ");
        }
        if(j < a.length) StdOut.print("...");
        StdOut.println();
    }
    public boolean isSorted() {
        for (int i = 0; i < a.length - 1; i++) {
            if (more(i,i+1)) {
                StdOut.println("sorter failed at " + i + ":" + a[i].toString());
                show(i-5>0? i-5:0, i+6 > a.length? a.length:i+6);
                return false;
            }
        }
        return true;
    }

    // override
    public void sort() {}
    public void sortTime(){
        long time = System.currentTimeMillis();
        sort();
        long costTime = System.currentTimeMillis() - time;
        if(isSorted()) StdOut.println(this.getClass().getSimpleName() + ": cost of time: " + costTime + " ms");
    }
}
