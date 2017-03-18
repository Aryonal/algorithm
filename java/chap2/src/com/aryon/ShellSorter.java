package com.aryon;

import com.prototype.Sorter;

/**
 *  Created by Dai on 2016/11/19.
 */
public class ShellSorter<T extends Comparable> extends Sorter<T> {

    private int StepLength;

    public ShellSorter(T[] a) {
        super(a);
        StepLength = 1;
        if(a.length > 4)
            for(; StepLength < a.length/3 ;) StepLength = StepLength*3+1;
    }

    @Override
    public void sort() {
        super.sort();
        for(; StepLength > 0; StepLength /= 3) {
            for(int j = StepLength; j < a.length; j += StepLength) {
                int t = j;
                while(t > 0 && less(t, t-StepLength)) {
                    exchange(t, t-StepLength);
                    t -= StepLength;
                }
            }
        }
    }
}
