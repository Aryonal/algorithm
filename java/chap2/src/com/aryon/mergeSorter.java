package com.aryon;

import com.prototype.Sorter;

/**
 * Created by Dai on 2016/11/19.
 */
public class mergeSorter<T extends Comparable> extends Sorter<T> {

    public mergeSorter(T[] a) {
        super(a);
    }

    @Override
    public void sort() {
        //super.sort();
        int s;
        for(s = 2; s <= a.length; s *= 2) {
            for(int i = 0; i < a.length; i += s) {
                if (i + s - 1 < a.length) {
                    merge(i, i + s - 1);
                }
                else {
                    if(s/2-1 < a.length-i) merge(i, i + s/2-1, a.length-1);
                }
            }
        }
        if(s/2 != a.length) merge(0, s/2-1, a.length-1);
    }

}
