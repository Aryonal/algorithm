package com.aryon.prototype;

import java.text.NumberFormat;

/**
 * Created by Dai on 2016/11/22.
 */
public class cNum implements Comparable<cNum> {
    double num;
    public cNum() {}
    public cNum(double n) {
        this.num = n;
    }

    public void set(double n) {
        this.num = n;
    }

    public boolean equals(cNum n) {
        return this.num == n.num;
    }

    public double getNum(){
        return num;
    }

    @Override
    public String toString() {
        return String.format("%g",num);
    }

    @Override
    public int compareTo(cNum o) {
        return this.num-o.num>0?1:this.num-o.num<0?-1:0;
    }
}
