package com.aryon.realize;

import com.aryonstd.out.toConsole;
import com.aryon.prototype.MaxPQ;

import java.lang.reflect.Array;

/**
 * Created by Dai on 2016/11/25.
 */
public class BinaryStack<T extends Comparable<T>> implements MaxPQ<T> {
    private int size;
    private int max;
    private T[] array;

    private Class<T> type;

    private void exchange(int i, int j) {
        T t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
    private void swim(int i) {
        if(i == 0) return;
        if(more(i, parent(i))) {
            exchange(i, parent(i));
            swim(parent(i));
        }
        return;
    }
    private void sink(int i) {
        if(leftChild(i) >= size) return;
        if(leftChild(i)+1 >= size) {
            if(more(leftChild(i), i)) exchange(leftChild(i), i);
            return;
        } else if(more(leftChild(i),i) || more(leftChild(i)+1, i)) {
            int target = more(leftChild(i), leftChild(i)+1) ? leftChild(i) : leftChild(i)+1;
            exchange(i, target);
            sink(target);
        }
    }
    private void extend(int delta) {
        T[] a = (T[]) Array.newInstance(type, max+delta);
        for(int i = 0; i < max; ++i) a[i] = array[i];
        max += delta;
        array = a;
    }
    private boolean more(int i, int j) {
        return array[i].compareTo(array[j]) > 0;
    }

    private static int parent(int i) {
        return (i-1)/2;
    }
    private static int leftChild(int i) {
        return i*2+1;
    }

    {
        this.max = 0;
        this.size = 0;
        this.array = null;
    }

    public BinaryStack(){
        this(0, null, null);
    }
    public BinaryStack(int max, T[] arr, Class<T> type) {
        this.type = type;
        extend(max);
        this.max = max; size = 0;
        for(T t : arr) {
            Insert(t);
        }
    }


    @Override
    public void Insert(T k) {
        if(size >= max) extend(max);
        array[size] = k;
        swim(size);
        size++;
    }

    @Override
    public T getMax() {
        return array[0];
    }

    @Override
    public T delMax() {
        T max = array[0];
        exchange(0, --size);
        sink(0);
        return max;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * item + space should be even
     * @param item
     * @param space
     */
    @Override
    public void print(int item, int space) {
        /*int layers = 0;
        int s = size-1;
        while(s != 0) {
            s = (s-1)/2;
            layers++;
        } // get number of layers
        StdOut.println("\nnumber of layers: " + layers);
        int flag = 0; //the last reference number of each layer, 0, 2, 6 ...
        int curr = 0; //current number of layer
        StdOut.print("layer:"+curr+" ");
        int squares = 0;
        for(int i = 0; i < layers; i++) squares = squares*2+(item+space)/2; // get space between two item of each layer

        for(int j = 0; j < squares; j++) StdOut.print(' ');
        StdOut.print(array[0].toString());
        for(int j = 0; j < squares+space; j++) StdOut.print(' ');

        char spc = ' '; char line = '-';
        for(int i = 1; i < size; i++) {
            if(i > flag) {
                flag += Math.pow(2,curr+1);
                curr++;
                squares = (squares-(item+space)/2)/2;
                StdOut.println();
                StdOut.print("layer:"+curr+" ");
            }
            for(int j = 0; j < squares; j++) StdOut.print(spc);
            StdOut.print(array[i].toString());
            for(int j = 0; j < squares+space; j++) StdOut.print(line);
            char t = spc; spc = line; line = t; // print line between two child with the same parent
        }
        StdOut.println();*/
        
        toConsole.printBinaryTree(array, item, space);
    }
}
