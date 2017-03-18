package com.aryon;

import com.prototype.Sorter;

/**
 * Created by Dai on 2016/11/21.
 */
public class quickSorter<T extends Comparable> extends Sorter<T> {

    public quickSorter(T[] a) {
        super(a);
    }

    @Override
    public void sort() {
        super.sort();
        quickSort(0, a.length-1);
    }

    private void quickSort(int lo, int hi) {
        if(lo >= hi) return;
        int mid = separation(lo, hi);
        quickSort(lo, mid-1);
        quickSort(mid+1, hi);
    }

    private int separation(int i, int j) {
        if(i==j) return i;
        int v = i; i++;
        while(i <= j) {
            if(!less(v, i)) i++;
            else if(!less(v, j)) {
                exchange(i, j);
                i++; j--;
            }
            else j--;
        }
        exchange(v, j);
        return j;
    }
}
