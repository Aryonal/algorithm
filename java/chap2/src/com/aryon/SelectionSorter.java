package com.aryon;

import com.prototype.Sorter;

/**
 * Created by Dai on 2016/11/18.
 */
public class SelectionSorter<T extends Comparable> extends Sorter<T> {

    public SelectionSorter(T[] a) {
        super(a);
    }

    @Override
    public void sort() {
        //super.sort();
        for(int i = 0; i < a.length-1; i++) {
            int min = i;
            for(int j = i; j < a.length; j++) {
                min = less(min, j) ? min : j;
            }
            exchange(min,i);
        }
    }

}
